package com.example.restfulwebservice.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.example.restfulwebservice.builder.service.ServiceApprovalService;
import com.example.restfulwebservice.builder.util.BeanUtils;
import com.example.restfulwebservice.builder.vo.FormFieldVO;
import com.example.restfulwebservice.builder.vo.ServiceFormVO;
import com.example.restfulwebservice.builder.vo.TemplateDataVO;

import lombok.extern.slf4j.Slf4j;


/**
 * 업무 처리
 */
@Slf4j
public class CodeServiceHandler  implements ServiceHandler {
	
	ServiceApprovalService serviceApprovalService;
	
    public static String isNullToString(Object object) {
        String string = "";
        if(object != null) {
            string = object.toString();
        }
        return string;
    }
    
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    
    
    @Override
    public Response handle(HttpServletRequest request, ServiceFormVO serviceForm, Map<String, Object> codeData, String actCode) throws Exception{
    	serviceApprovalService = (ServiceApprovalService) BeanUtils.getBean("serviceApprovalRestService");
        log.debug("codeData ("+actCode+") : " + codeData);
        
        String userId = "userId";
        String userName = "userName";
        String companyCode = "H101";
        String workplaceCode = "1";
        String deptCode = "DEPT";
        
        Response response = new Response();
        String resultCode = "0";
        String resultMessage = "search";
        
        // 조회
        if(actCode.equals("submit")) {

        // 저장
        }else if(actCode.equals("insert")) {
            
            // vo에 저장 column, data setting
            TemplateDataVO dataVo = new TemplateDataVO();
            dataVo.setTableName(serviceForm.getDbTable());
            
            // 해당 업무 Field 조회
            List<FormFieldVO> fieldList = new ArrayList<FormFieldVO>();
            try {
                FormFieldVO paramVo = new FormFieldVO();
                paramVo.setFormId(serviceForm.getFormId());
                fieldList = serviceApprovalService.selectFormFieldList(paramVo);
            } catch(Exception e) {
                e.printStackTrace();
            }            
            
            HashMap columnData = new HashMap();
            
            for(int j=0; j<fieldList.size(); j++) {
                FormFieldVO fieldVo = (FormFieldVO)fieldList.get(j);
                
                String key = fieldVo.getFieldId();
                String value = isNullToString(codeData.get(key.toUpperCase()));

                if("NUMBER".equals(fieldVo.getFieldType())) { 
                	value = value.replaceAll(",", "");
                	value = value.replaceAll(" ", "");
                }else if("DATE".equals(fieldVo.getFieldType())) {
                	value = value.replaceAll("-", "");
                	value = value.replaceAll("\\.", "");
                	value = value.replaceAll(" ", "");
                }
                
                if("COMPANY_CODE".equals(key)) {
                	value = companyCode;
                }else if("CREATE_TIME".equals(key) || "UPDATE_TIME".equals(key)) {
                    value = "SYSDATE";
                }else if("CREATE_USER".equals(key) || "UPDATE_USER".equals(key)) {
                    value = userId;
                }
                
                if(key.indexOf("FILE") < 0) {
	                value = isNullToString(value);
	                columnData.put(key, value);
                }
            }
            
            dataVo.setColumnNames(columnData);
            dataVo.setColumnValues(columnData);
            
            try {
                serviceApprovalService.insertCodeTemplateData(dataVo);
            } catch(Exception e) {
            	e.printStackTrace();
                resultCode = "ERR-199999";
                resultMessage = "저장시 오류가 발생했습니다. 확인하시기 바랍니다.";
            }

            
        // 수정
        }else if(actCode.equals("update")) {
            // vo에 저장 column, data setting
            TemplateDataVO dataVo = new TemplateDataVO();
            dataVo.setTableName(serviceForm.getDbTable());  
            
            // 해당 업무 Field 조회
            List<FormFieldVO> updateFieldList = new ArrayList<FormFieldVO>();
            try {
                FormFieldVO paramVo = new FormFieldVO();
                paramVo.setFormId(serviceForm.getFormId());
                paramVo.setFieldPkAt("N");
                updateFieldList = serviceApprovalService.selectFormFieldList(paramVo);
            } catch(Exception e) {
                e.printStackTrace();
            }            
            
            HashMap columnData = new HashMap();
            
            for(int j=0; j<updateFieldList.size(); j++) {
                FormFieldVO fieldVo = (FormFieldVO)updateFieldList.get(j);
                
                String key = fieldVo.getFieldId();
                String value = (String)codeData.get(key.toUpperCase());

                if("COMPANY_CODE".equals(key)) {
                	value = companyCode;
                }else if("UPDATE_TIME".equals(key)) {
                    value = "SYSDATE";
                }else if("UPDATE_USER".equals(key)) {
                    value = userId;
                }
                
                // 필드가 첨부파일 필드가 아닌경우만 수정(첨부파일 컬럼은 별도로 업데이트 처리함)
                if(key.indexOf("FILE") < 0) {
                	
                	if(key.indexOf("PASSWORD") > -1) {
                		// PASSWORD 필드일경우 값이 있을때만 업데이트 없을경우는 수정하지 않음
                		if(!isEmpty(value)) {
    	                    value = isNullToString(value);
    	                   	columnData.put(key, value);
                		}
                	}else {
                		value = isNullToString(value);
                		columnData.put(key, value);
                	}
                }
            }
            
            dataVo.setColumnNames(columnData);
            dataVo.setColumnValues(columnData);
            
            // 해당 업무 Field 조회
            List<FormFieldVO> whereFieldList = new ArrayList<FormFieldVO>();
            try {
                FormFieldVO paramVo = new FormFieldVO();
                paramVo.setFormId(serviceForm.getFormId());
                paramVo.setFieldPkAt("Y");
                whereFieldList = serviceApprovalService.selectFormFieldList(paramVo);
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            ArrayList whereValues = new ArrayList();
            HashMap whereMap = new HashMap();
            for(int j=0; j<whereFieldList.size(); j++) {
                FormFieldVO fieldVo = (FormFieldVO)whereFieldList.get(j);
                
                String key = fieldVo.getFieldId();
                String value = (String)codeData.get(key.toUpperCase());
                
                if("COMPANY_CODE".equals(key)) {
                	value = companyCode;
                }else if("WORKPLACE_CODE".equals(key)) {
                	if(isEmpty(value)) value = workplaceCode; 
                }
                
                value = isNullToString(value);
               	whereMap.put(key, value);
            }
            whereValues.add(whereMap);
            dataVo.setWhereValues(whereValues);
            
            int returnValue = 0;
            try {
                serviceApprovalService.updateCodeTemplateData(dataVo);
            } catch(Exception e) {
                resultCode = "ERR-199999";
                resultMessage = "수정시 문제가 발생됬습니다.";
            }
            
            // 첨부파일 등록
            codeData.put("dbTable", serviceForm.getDbTable());
            codeData.put("formId", serviceForm.getFormId());
            //this.insertServiceFile(request, codeData, userInfo);      
         
        // 삭제
        }else if(actCode.equals("delete")) {
           
            // vo에 저장 column, data setting
            TemplateDataVO dataVo = new TemplateDataVO();
            dataVo.setTableName(serviceForm.getDbTable());                      
            
            // 해당 업무 Field 조회
            List<FormFieldVO> fieldList = new ArrayList<FormFieldVO>();
            try {
                FormFieldVO paramVo = new FormFieldVO();
                paramVo.setFormId(serviceForm.getFormId());
                paramVo.setFieldPkAt("Y");
                fieldList = serviceApprovalService.selectFormFieldList(paramVo);
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            ArrayList whereValues = new ArrayList();
            HashMap whereMap = new HashMap();
            for(int j=0; j<fieldList.size(); j++) {
                FormFieldVO fieldVo = (FormFieldVO)fieldList.get(j);
                
                String key = fieldVo.getFieldId();
                String value = (String)codeData.get(key.toUpperCase());
                
                value = isNullToString(value);
               	whereMap.put(key, value);
            }
            whereValues.add(whereMap);
            dataVo.setWhereValues(whereValues);
            
            int returnValue = 0;
            try {
                serviceApprovalService.deleteCodeTemplateData(dataVo);
            } catch(Exception e) {
                resultCode = "ERR-199999";
                resultMessage = "삭제시 문제가 발생했습니다.";
            }
        }else {
            resultCode = "ERR-199999";
            resultMessage = "NO MATCH ACTION CODE";
        }        
        
        response.setResultCode(resultCode);
        response.setResultMessage(resultMessage);
        response.setBizCode("");
        response.setActCode(actCode);
        response.setUserId(userId);
        return response;
    }
}