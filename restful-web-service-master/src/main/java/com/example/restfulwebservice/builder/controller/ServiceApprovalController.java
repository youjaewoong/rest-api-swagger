package com.example.restfulwebservice.builder.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restfulwebservice.builder.Response;
import com.example.restfulwebservice.builder.service.ServiceApprovalService;
import com.example.restfulwebservice.builder.service.TaskService;
import com.example.restfulwebservice.builder.util.AjaxResponseUtil;
import com.example.restfulwebservice.builder.util.ComUtil;
import com.example.restfulwebservice.builder.util.JsonUtil;
import com.example.restfulwebservice.builder.vo.FormButtonVO;
import com.example.restfulwebservice.builder.vo.FormFieldVO;
import com.example.restfulwebservice.builder.vo.ServiceFormVO;
import com.example.restfulwebservice.builder.vo.TemplateDataVO;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class ServiceApprovalController {
	
	@Autowired
	ServiceApprovalService serviceApprovalService;

    @RequestMapping(value = "/serviceCodeForm.do")
    public String approvalForm(@RequestParam(required=false) String serviceId 
                                , ModelMap model) throws Exception {

        ServiceFormVO paramVo = new ServiceFormVO();
        paramVo.setServiceId(serviceId);
        paramVo.setLanguageCode("Ko");
        ServiceFormVO serviceFormVO = serviceApprovalService.selectServiceInfo(paramVo);
        // 정상일경우 해당업무 Template 으로 이동 
        model.addAttribute("serviceInfo", serviceFormVO);       // 서비스(업무) 정보
        model.addAttribute("serviceId", serviceId);             // Service Id
        return "builder/template-01";
    }
    
    @RequestMapping("/user/service/code/getFormButtonList.do")
    public ResponseEntity<String> getFormButtonList(@RequestParam String serviceId, 
                                                ModelMap model) throws Exception {
    	
        Map<String,Object> returnObj = new HashMap<>();
        // 해당 업무 버튼 조회
        List<FormButtonVO> formButtonList = null;
        try {
            FormButtonVO paramVo = new FormButtonVO();
            paramVo.setCompanyCode("H101");
            paramVo.setFormId("fromId123");
            paramVo.setRoleId("V_ROLE_USER");
            formButtonList = serviceApprovalService.selectFormButtonList(paramVo);
        } catch(Exception e) {
            e.printStackTrace();
        }
    
        returnObj.put("returnCode", "SUCCESS");
        returnObj.put("formButtonList", formButtonList);
    
        model.addAttribute("returnObj", returnObj);
        
        return AjaxResponseUtil.getResponseEntity(model);
    }
    
    @RequestMapping("/user/service/code/getFormInfo.do")
    public ResponseEntity<String> getFormInfo(@RequestParam String serviceId, 
    										  ModelMap model) throws Exception{
        // 해당 업무 정보 조회
        ServiceFormVO serviceFormVo = null;
        try {
            ServiceFormVO paramVo = new ServiceFormVO();
            paramVo.setServiceId(serviceId);
            serviceFormVo = serviceApprovalService.selectServiceInfo(paramVo);
        } catch(Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("serviceFormInfo", serviceFormVo);
        return AjaxResponseUtil.getResponseEntity(model);
    }
    
    @RequestMapping("/user/service/code/getFormFieldList.do")
    public ResponseEntity<String> getFormFieldList(@RequestParam String serviceId, 
    												ModelMap model) throws Exception{
    	HashMap<String, Object> returnObj = new HashMap<>();
        List<FormFieldVO> formFieldList = new ArrayList<>();
        try {
            FormFieldVO paramVo = new FormFieldVO();
            paramVo.setFormId("fromId123");
            formFieldList = serviceApprovalService.selectFormFieldList(paramVo);
            
            returnObj.put("returnCode", "SUCCESS");
            returnObj.put("formFieldList", formFieldList);	            
        } catch(Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("returnObj", returnObj);
        return AjaxResponseUtil.getResponseEntity(model);
    }
    
    @RequestMapping("/user/service/code/getGridDataList.do")
    public ResponseEntity<String> getGridDataList(@RequestParam String serviceId, 
    											 @RequestParam String searchParams, 
                                                 ModelMap model) throws Exception {
        
        Map<String, Object> returnObj = new HashMap<>();
        String returnCode = "";
        String userId = "TEST_USER";
        String companyCode = "H101";
        
        // 해당 업무 정보 조회
        ServiceFormVO serviceFormVo = null;
        try {
            ServiceFormVO paramVo = new ServiceFormVO();
            paramVo.setServiceId(serviceId);
            serviceFormVo = serviceApprovalService.selectServiceInfo(paramVo); 
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (serviceFormVo!=null && !"".equals(serviceFormVo.getDbTable()))  {

        	// table 정보 조회
            String bizTableName = serviceFormVo.getDbTable();

            // 해당 업무 Field 조회
            List<FormFieldVO> fieldList = new ArrayList<FormFieldVO>();
            try {
                FormFieldVO paramVo = new FormFieldVO();
                paramVo.setFormId(serviceFormVo.getFormId());
                paramVo.setUseAt("Y");
                fieldList = serviceApprovalService.selectFormFieldList(paramVo);
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            // vo에 저장 column, data setting
            TemplateDataVO dataVo = new TemplateDataVO();
            dataVo.setTableName(bizTableName);
            
            // 테이블 컬럼정보
            HashMap columnName = new HashMap();
            for(int i=0; i<fieldList.size(); i++) {
                FormFieldVO fieldVo = (FormFieldVO)fieldList.get(i);
                if(fieldVo.getFieldId()!=null && !"".equals(fieldVo.getFieldId())) {
                    String mapKey = fieldVo.getFieldId(); 
                    String mapValue = fieldVo.getFieldId(); 
                    columnName.put(mapKey, mapValue);
                }
            }
            dataVo.setColumnNames(columnName);
            
            // Where 정보(searchParams)
            HashMap<String, Object> searchJsonParam = (HashMap<String, Object>)JsonUtil.JsonToMap(ComUtil.expReverseFilter(searchParams)); 
            
            // 삭제 대상 필드명을 담는 List
            List<String> removeKeys = new ArrayList<String>();
            
            // 방법1
            Iterator<String> keys = searchJsonParam.keySet().iterator();
            while( keys.hasNext() ){
                String key = keys.next();
                
                String value = isNullToString(searchJsonParam.get(key));
                if(value.indexOf("AND") > -1 ) {
                    // 기간 검색을 위해 기존 KEY를 제거후 between위해 startYmd, endYmd에 넣는다  
                	// searchJsonParam.remove(key); 기존코드,
                    removeKeys.add(key);
                    
                	String[] betweenYmd = value.split("AND");
                	if(betweenYmd.length == 2) {
                		if(betweenYmd[0].length() == 8 && betweenYmd[1].length() == 8) {
                			dataVo.setColumnValue(key);
                			dataVo.setStartYmd(betweenYmd[0]);
                			dataVo.setEndYmd(betweenYmd[1]);
                		}
                	}
                } else {
                    if(key.indexOf("_CODE")< 0) {
                    	searchJsonParam.put(key, searchJsonParam.get(key)+"%");
                    }	   
                }
            }
            
            // 제거대상 keys 제거한다.
            Iterator<String> removeKeysIter = removeKeys.iterator();
            while(removeKeysIter.hasNext()){
                String removeKey = removeKeysIter.next(); 
                try{
                    searchJsonParam.remove(removeKey);
                    
                // 중복 키 발생할 경우 ignore
                }catch(Exception e){
                    // ignore
                    continue;
                }
            }
            
            ArrayList whereValues = new ArrayList();
            searchJsonParam.put("COMPANY_CODE", companyCode);
            whereValues.add(searchJsonParam);
            dataVo.setWhereValues(whereValues);
            
            List<Map<String,Object>> tempDataList = serviceApprovalService.selectCodeTemplateDataList(dataVo);
            returnObj.put("templateDataList", tempDataList);
            returnObj.put("returnCode", "SUCCESS");
        }
        model.addAttribute("returnObj", returnObj);
        return AjaxResponseUtil.getResponseEntity(model);
    }
    
    
    @RequestMapping("/user/service/{actCode}/serviceCodeProcess.do")
    public ResponseEntity<String> saveApprovalData(@RequestParam String codeData, 
                                                    @RequestParam String serviceId, 
                                                    @PathVariable String actCode,
                                                    @RequestParam(required=false) String delFileParams,
                                                    ModelMap model,
                                                    HttpServletRequest request) throws Exception {
        HashMap returnObj = new HashMap();
        codeData = "&#123;&quot;serviceId&quot;&#58;&quot;4f204429c87a43c6b04c6f418b37d906&quot;,&quot;WORKPLACE_CODE&quot;&#58;&quot;1&quot;,&quot;WORKPLACE_NAME&quot;&#58;&quot;본사&quot;,&quot;BASE_DATE&quot;&#58;&quot;20220408&quot;,&quot;EXCHANGE_RATE&quot;&#58;&quot;1100&quot;,&quot;USE_AT&quot;&#58;&quot;Y&quot;&#125;";
        
        // 해당 업무 정보 조회
        ServiceFormVO serviceFormVo = null;
        try {
            ServiceFormVO paramVo = new ServiceFormVO();
            paramVo.setServiceId(serviceId);
            serviceFormVo = serviceApprovalService.selectServiceInfo(paramVo); 
        } catch(Exception e) {
            e.printStackTrace();
        }
        
            try {
            	String filterStr = ComUtil.expReverseFilter(codeData);
            	Map<String, Object> jsonDataParam = new ObjectMapper().readValue(filterStr, Map.class);

	            // 업무 처리 시작
        		TaskService taskService = new TaskService();
                
	            // 업무 처리(업무테이블 입력)
	            Response response = taskService.executeCode(request, serviceFormVo, jsonDataParam, actCode);
	            
	            // 삭제파일이 있을경우
	            if(delFileParams != null) { 
	            	//deleteFileInfo(serviceFormVo, userInfo, jsonDataParam, delFileParams);
	            }
	            
	            if("0".equals(response.getResultCode())) {
	            	returnObj.put("returnCode", "SUCCESS");    	
	            }else {
	                returnObj.put("returnCode", "FAIL");
	                returnObj.put("returnMessage", response.getResultMessage());
	            }
	            
	        } catch (Exception e) {
	            returnObj.put("returnCode", "FAIL");
	            returnObj.put("returnMessage", "저장시 문제가 발생하였습니다.");
	        }
        
        model.addAttribute("returnObj", returnObj);
        return AjaxResponseUtil.getResponseEntity(model);        
    }
    
    public String isNullToString(Object object) {
        String string = "";
        if(object != null) {
            string = object.toString();
        }
        return string;
    }

}
