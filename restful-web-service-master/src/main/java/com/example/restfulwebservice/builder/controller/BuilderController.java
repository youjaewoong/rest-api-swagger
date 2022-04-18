package com.example.restfulwebservice.builder.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restfulwebservice.builder.service.BuilderService;
import com.example.restfulwebservice.builder.util.AjaxResponseUtil;
import com.example.restfulwebservice.builder.util.ComUtil;
import com.example.restfulwebservice.builder.vo.WfServiceVO;
import com.example.restfulwebservice.builder.vo.WmFormButtonVO;
import com.example.restfulwebservice.builder.vo.WmFormFieldVO;
import com.example.restfulwebservice.builder.vo.WmFormItemVO;
import com.example.restfulwebservice.builder.vo.WmFormsVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BuilderController {
    
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	BuilderService builderService;
	
	@GetMapping("/builder/index")
	public String indxe(ModelMap model, HttpServletRequest request) throws Exception {
	    String formId = UUID.randomUUID().toString().replace("-", "");
	    String serviceId = UUID.randomUUID().toString().replace("-", "");
	    model.addAttribute("formId", formId);
	    model.addAttribute("serviceId", serviceId);
	    model.addAttribute("companyCode", "H101");
		return "builder/index";
	}
	
    /**
     * 서비스 기본정보 등록 Step 1
     */
    @RequestMapping("/admin/builder/builder/setSvcInfoForBuilder.do")
    public ResponseEntity<String> setSvcInfoForBuilder (@RequestParam Map<String, Object> map, ModelMap model) throws Exception {
    		WfServiceVO wfServiceVo = objectMapper.convertValue(map, WfServiceVO.class);
            int resultCode = 0;
            try {
                resultCode = builderService.insertService(wfServiceVo);
            } catch(Exception e) {
                e.printStackTrace();
                resultCode = -99;
            }
            if(resultCode > 0) {
                model.addAttribute("CODE", HttpStatus.OK);
                model.addAttribute("MESSAGE", "서비스 생성이 완료되었습니다.");
            } else {
                model.addAttribute("CODE", HttpStatus.BAD_REQUEST);
                model.addAttribute("MESSAGE", "서비스 생성에 실패하였습니다.");
            }
        return AjaxResponseUtil.getResponseEntity(model);
    }
    
    
    /**
     * 업무테이블 생성 Step 2
     * Statements
     *
     * @param map
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/builder/builder/setBizTableForBuilder.do")
    public ResponseEntity<String> setBizTableForBuilder (@RequestParam Map<String, Object> map, ModelMap model) throws Exception {
    		WfServiceVO wfServiceVo = objectMapper.convertValue(map, WfServiceVO.class);
    		
            // 테이블 배열 생성
            List<String> tables = new ArrayList<String>();
            tables.add(wfServiceVo.getDbTable());
            if("Y".equals(wfServiceVo.getRetirerSepratAt())) {
                tables.add(wfServiceVo.getDbTable() + "_OUT");
            }
            
            for(String tableName : tables) {
                
                wfServiceVo.setDbTable(tableName);
                
                	// 테이블 생성 전 존재여부 체크
                    try {
                        
                        // 데이터 필드 json 데이타
                        String fieldItemData = (String)map.get("fieldItems");
                        List<WmFormFieldVO> tempFieldItemList = objectMapper.readValue(fieldItemData, 
                        		new TypeReference<List<WmFormFieldVO>>(){});
                        
                        // 업무테이블 생성
                        StringBuffer buff = new StringBuffer();
                        
                        // 업무테이블 생성
                        buff.append(this.createBizTableHeader(wfServiceVo.getDbTable(), tempFieldItemList))
                        	.append(this.createBizTableTail(wfServiceVo.getDbTable(), tempFieldItemList));
                                
                        Map<String, Object> mapper = new HashMap<String, Object>();
                        mapper.put("query", buff.toString());
                        
                        int resultCode = 0;
                        try {
                            builderService.createBizTable(mapper);
                        } catch(Exception e) {
                            e.printStackTrace();
                            resultCode = -99;
                        }

                        // Comment 생성
                        ArrayList<String> commentList = new ArrayList<String>();
                        for(WmFormFieldVO wmFormFieldVo : tempFieldItemList) {
                            // 사용여부가 Y인것만 컬럼 및 코멘트 생성
                            if("Y".equals(wmFormFieldVo.getUseAt())) {
                                String commentSQL = "COMMENT ON COLUMN " + wfServiceVo.getDbTable() +"." + wmFormFieldVo.getFieldId() + " IS '" + wmFormFieldVo.getFieldName() + "'";
                                commentList.add(commentSQL);
                            }
                        }
                        
                        String tailColumn[] = {"CREATE_USER:생성자", "CREATE_TIME:생성시간", "UPDATE_USER:수정자", "UPDATE_TIME:수정시간"};
                        for(String column : tailColumn)  {
                            String[] col = column.split(":");
                            String commentSQL = "COMMENT ON COLUMN " + wfServiceVo.getDbTable() +"." + col[0] + " IS '" + col[1] + "'";
                            commentList.add(commentSQL);
                        }
                                                                
                        try {
                            for(String query : commentList) {
                                Map<String, Object> param = new HashMap<String, Object>();
                                mapper.put("query", query);
                                resultCode += builderService.createBizTableComment(param);
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                            resultCode = -99;
                        }

                        // Table Synonym 생성
                        String grant = "GRANT SELECT, INSERT, DELETE, UPDATE ON " + wfServiceVo.getDbTable() + " TO HBSLADM";
                        
                        Map<String, Object> grantMapper = new HashMap<String, Object>();
                        grantMapper.put("query", grant);
                        
                        /**
                         * Grant 생성부분 일단 주석처리
                        */
                        try {
                            //resultCode = builderService.createBizTableGrant(grantMapper);
                        	
                        } catch(Exception e) {
                            e.printStackTrace();
                            resultCode = -99;
                        }
                        
                        if(!wfServiceVo.getDbTable().contains("_OUT")) {
                            // WM_FORM_FIELD 테이블 INSERT
                            List<WmFormFieldVO> fieldItemList = new ArrayList<WmFormFieldVO>();
                            
                            for(WmFormFieldVO wmFormFieldVo : tempFieldItemList) {
                                wmFormFieldVo.setCompanyCode(wfServiceVo.getCompanyCode());
                                wmFormFieldVo.setFormId(wfServiceVo.getFormId());
                                wmFormFieldVo.setFieldCat("A");
                                
                                fieldItemList.add(wmFormFieldVo);
                            }
                            
                            fieldItemList.addAll(wmFormFieldHeader(wfServiceVo.getCompanyCode(), wfServiceVo.getFormId()));
                            
                            try {
                                for(WmFormFieldVO wmFormFieldVo : fieldItemList) {
                                	resultCode = builderService.insertFormField(wmFormFieldVo);
                                }
                            } catch(Exception e) {
                                e.printStackTrace();
                                resultCode = -99;
                            }
                        }
                        
                        if(resultCode <= 0) throw new Exception();
                        
                        model.addAttribute("CODE", HttpStatus.OK);
                        model.addAttribute("MESSAGE", "테이블 생성이 완료되었습니다.");  
                        
                    } catch(Exception e) {
                        model.addAttribute("CODE", HttpStatus.BAD_REQUEST);
                        model.addAttribute("MESSAGE", "테이블 생성에 실패하였습니다.");
                    }
            }
        return AjaxResponseUtil.getResponseEntity(model);
    }
    
    
    /**
     * 폼 아이템 저장 - Step 3
     */
    @RequestMapping("/admin/builder/builder/setItemForBuilder.do")
    public ResponseEntity<String> setItemForBuilder (@RequestParam Map<String, Object> map, ModelMap model) throws Exception {
        
        	WfServiceVO wfServiceVo = objectMapper.convertValue(map, WfServiceVO.class);
            int resultCode = 0;
            try {
                
                List<WmFormItemVO> formItemList = new ArrayList<WmFormItemVO>();
                String buildData = (String)map.get("buildItems");
                List<WmFormItemVO> tempFormItemList = objectMapper.readValue(buildData, 
                		new TypeReference<List<WmFormItemVO>>(){});
                
                for(WmFormItemVO wmFormItemVo : tempFormItemList) {
                    wmFormItemVo.setCompanyCode(wfServiceVo.getCompanyCode());
                    wmFormItemVo.setFormId(wfServiceVo.getFormId());
                    wmFormItemVo.setCompanyCode("H101");
                    wmFormItemVo.setCreateUser("TEST_USER");
                    wmFormItemVo.setUpdateUser("TEST_USER");
                    formItemList.add(wmFormItemVo);
                }

                // WF_FORM_ITEM 등록
                try {
                    for(WmFormItemVO wmFormItemVo : formItemList) {
                    	resultCode = builderService.insertFormItem(wmFormItemVo);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    resultCode = -99;
                }      
                
                // WF_FORMS 등록
                WmFormsVO wmFormsVo = new WmFormsVO();
                
                wmFormsVo.setCompanyCode(wfServiceVo.getCompanyCode());
                wmFormsVo.setFormId(wfServiceVo.getFormId());
                wmFormsVo.setFormName(wfServiceVo.getServiceName());
                wmFormsVo.setFormDesc(wfServiceVo.getServiceName());
                wmFormsVo.setFormTmplId((String)map.get("formTmplId"));            
                wmFormsVo.setJson((String)map.get("json"));
                wmFormsVo.setHtml((String)map.get("html"));
                wmFormsVo.setOriginJson((String)map.get("originJson"));
                wmFormsVo.setJscript((String)map.get("jscript"));
                
                // WF_FORMS Insert
                try {
                    resultCode = builderService.insertForms(wmFormsVo);
                } catch(Exception e) {
                    e.printStackTrace();
                    resultCode = -99;
                }
                
                if(resultCode <= 0) throw new Exception();
                
                model.addAttribute("CODE", HttpStatus.OK);
                model.addAttribute("MESSAGE", "아이템 생성이 완료되었습니다.");
                
            } catch(Exception e) {
                resultCode = -99;
                model.addAttribute("CODE", HttpStatus.BAD_REQUEST);
                model.addAttribute("MESSAGE", "아이템 생성에 실패하였습니다.");
        }
        return AjaxResponseUtil.getResponseEntity(model);
    }
    
    /**
     * 버튼 권한 저장 - Step 4
     * Statements
     *
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/builder/builder/setPermissionForBuilder.do")
    public ResponseEntity<String> setPermissionForBuilder (@RequestParam Map<String, Object> map
            , HttpServletRequest request
            , ModelMap model) throws Exception {
        
        
    		WfServiceVO wfServiceVo = objectMapper.convertValue(map, WfServiceVO.class);
            int resultCode = 0;
            try {
                
                String permission = (String)request.getParameter("permission");
                List<WmFormButtonVO> permissionList = objectMapper.readValue(permission, 
                		new TypeReference<List<WmFormButtonVO>>(){});
                
                List<WmFormButtonVO> wmFormButtonList = new ArrayList<WmFormButtonVO>();
                
                for(WmFormButtonVO wmFormButtonVo : permissionList) {
                    wmFormButtonVo.setCompanyCode(wfServiceVo.getCompanyCode());
                    wmFormButtonVo.setFormId(wfServiceVo.getFormId());
                    
                    if(wmFormButtonVo.getPermission() != null) {
                        String perm[] = wmFormButtonVo.getPermission().split(":");
                        wmFormButtonVo.setRoleId(perm[0]);
                        wmFormButtonVo.setButtonCode(perm[1]);
                        wmFormButtonVo.setUseAt(perm[2]);
                    }
                    wmFormButtonVo.setCreateUser("TEST_USER");
                    wmFormButtonList.add(wmFormButtonVo);
                }
                
                // WF_FORM_BUTTON Insert
                try {
                    for(WmFormButtonVO wmFormButtonVo : wmFormButtonList) {
                    	resultCode = builderService.insertWmFormButton(wmFormButtonVo);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    resultCode = -99;
                }
                
                if(resultCode <= 0) throw new Exception();
                
                model.addAttribute("CODE", HttpStatus.OK);
                model.addAttribute("MESSAGE", "권한 생성이 완료되었습니다.");
                
            } catch(Exception e) {
                resultCode = -99;
                model.addAttribute("CODE", HttpStatus.BAD_REQUEST);
                model.addAttribute("MESSAGE", "권한 생성에 실패하였습니다.");
            }
        return AjaxResponseUtil.getResponseEntity(model);
    }    
    
    
    /**
     * 폼빌더 미리보기 화면
     * Statements
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/builder/builder/preview.do")
    public String preview(ModelMap model, HttpServletRequest request) throws Exception{
        
        String jsonStr = (String)request.getParameter("json");
        String serviceName = (String)request.getParameter("serviceName");
        model.addAttribute("jsonStr", ComUtil.expReverseFilter(jsonStr));
        model.addAttribute("serviceName", serviceName);
        return "/builder/builder/preview";
    }       
    
    
    /**
     * 테이블 생성 스크립트 header 부분 조합
     * Statements
     *
     * @param tableName
     * @param fieldItemList
     * @return
     */
    private String createBizTableHeader(String tableName, List<WmFormFieldVO> fieldItemList) {
        StringBuffer buff = new StringBuffer();

        buff.append("CREATE TABLE " + tableName + "\n");
        buff.append("(" + "\n");

        for(WmFormFieldVO wmFormFieldVo : fieldItemList) {
        	
        	// 사용여부가 Y인것만 컬럼 및 코멘트 생성            
        	if("Y".equals(wmFormFieldVo.getUseAt())) {
        	
	            buff.append("\t");
	            buff.append(wmFormFieldVo.getFieldId().toUpperCase() + "\t");
	            buff.append(wmFormFieldVo.getFieldType());
	            
	            if(!wmFormFieldVo.getFieldType().toUpperCase().equals("DATE") && !wmFormFieldVo.getFieldType().toUpperCase().equals("NUMBER")) {
	                buff.append("(" + wmFormFieldVo.getFieldLength() + ")");
	            }
	
	            if(!wmFormFieldVo.getFieldDefault().equals("") && !wmFormFieldVo.getFieldType().toUpperCase().equals("DATE")) {
	                buff.append("\t");
	                if(wmFormFieldVo.getFieldType().toUpperCase().equals("NUMBER")) {
	                    buff.append("DEFAULT " + wmFormFieldVo.getFieldDefault());
	                } else {
	                    buff.append("DEFAULT '" + wmFormFieldVo.getFieldDefault() + "'");
	                }
	            }
	            
	            if(wmFormFieldVo.getFieldNullableAt().equals("N")) {
	                buff.append("\t");
	                buff.append("NOT NULL");
	            }
	            
	            buff.append("," + "\n");
        	}
        }
        return buff.toString();
    }
    
    
    /**
     * 테이블 생성 스크립트 tail 부분 조합
     * Statements
     *
     * @param tableName
     * @param fieldItemList
     * @return
     */
    private String createBizTableTail(String tableName, List<WmFormFieldVO> fieldItemList) {
        StringBuffer buff = new StringBuffer();
        
        buff.append("\t" + "CREATE_USER    VARCHAR(20)," + "\n");
        buff.append("\t" + "CREATE_TIME    DATE," + "\n");
        buff.append("\t" + "UPDATE_USER    VARCHAR(20)," + "\n");
        buff.append("\t" + "UPDATE_TIME    DATE" + "\n");
        
        // PK 설정
        buff.append("\t" + ", CONSTRAINT " + "PK_" + tableName + " PRIMARY KEY(");
        
        String keys = "";
        for(WmFormFieldVO wmFormFieldVo : fieldItemList) {
            if(wmFormFieldVo.getFieldPkAt().equals("Y")) {
                keys += wmFormFieldVo.getFieldId() + ",";
            }
        }
        if(keys != "") {
        	buff.append(keys.substring(0, keys.length()-1));
        }
        buff.append(")" + "\n");
        buff.append(");");
        
        return buff.toString();
    }
    
    
    public List<WmFormFieldVO> wmFormFieldHeader(String companyCode, String formId) {
        
        List<WmFormFieldVO> fieldItemList = new ArrayList<WmFormFieldVO>();
        
        WmFormFieldVO vo = new WmFormFieldVO();   
        
        vo = new WmFormFieldVO();
        vo.setCompanyCode(companyCode);
        vo.setFormId(formId);
        vo.setFieldId("CREATE_TIME");
        vo.setFieldName("CREATE_TIME");
        vo.setFieldType("DATE");
        vo.setFieldLength(0);
        vo.setFieldNullableAt("Y");
        vo.setFieldPkAt("N");
        vo.setFieldCat("A");
        vo.setUseAt("Y");
        
        fieldItemList.add(vo);    
        
        vo = new WmFormFieldVO();
        vo.setCompanyCode(companyCode);
        vo.setFormId(formId);
        vo.setFieldId("CREATE_USER");
        vo.setFieldName("CREATE_USER");
        vo.setFieldType("VARCHAR2");
        vo.setFieldLength(20);
        vo.setFieldNullableAt("Y");
        vo.setFieldPkAt("N");
        vo.setFieldCat("A");
        vo.setUseAt("Y");
        
        fieldItemList.add(vo);            
        
        vo = new WmFormFieldVO();
        vo.setCompanyCode(companyCode);
        vo.setFormId(formId);
        vo.setFieldId("UPDATE_TIME");
        vo.setFieldName("UPDATE_TIME");
        vo.setFieldType("DATE");
        vo.setFieldLength(0);
        vo.setFieldNullableAt("Y");
        vo.setFieldPkAt("N");
        vo.setFieldCat("A");
        vo.setUseAt("Y");
        
        fieldItemList.add(vo);    
        
        vo = new WmFormFieldVO();
        vo.setCompanyCode(companyCode);
        vo.setFormId(formId);
        vo.setFieldId("UPDATE_USER");
        vo.setFieldName("UPDATE_USER");
        vo.setFieldType("VARCHAR2");
        vo.setFieldLength(20);
        vo.setFieldNullableAt("Y");
        vo.setFieldPkAt("N");
        vo.setFieldCat("A");
        vo.setUseAt("Y");
        
        fieldItemList.add(vo);            
        return fieldItemList;
    }
}