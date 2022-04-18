package com.example.restfulwebservice.builder.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.restfulwebservice.builder.BizServiceValidator;
import com.example.restfulwebservice.builder.CodeServiceHandler;
import com.example.restfulwebservice.builder.Response;
import com.example.restfulwebservice.builder.ServiceHandler;
import com.example.restfulwebservice.builder.Validator;
import com.example.restfulwebservice.builder.ValidatorFactory;
import com.example.restfulwebservice.builder.vo.ServiceFormVO;
import com.example.restfulwebservice.builder.vo.ValidationVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TaskService {
    
    /**
     * Request 데이터 정합성 체크.
     * Validator가 SUCCESS 결과를 반환하면 성공 그외에는 모두 실패.
     *
     * @param exchange
     */
    
    public ValidationVO validate(HttpServletRequest request, ServiceFormVO serviceForm, HashMap approvalData, String actCode) throws Exception {
    	log.info("TaskService:validate started.");
    	
    	// 업무코드 
        String bizCode = serviceForm.getBizCode();
        
        // 업무별 Validator get
        Validator validator = ValidatorFactory.getInstance().getValidator(bizCode);
        
        ValidationVO result;
        if(validator != null) {
        	//validation처리.
        	result = validator.validate(request, serviceForm, approvalData, actCode);
        }else {
        	// Validator 없을경우(기본)
        	validator = new BizServiceValidator();
        	result = validator.validate(request, serviceForm, approvalData, actCode);
        }
        log.info("TaskService:validate result : "+result.toString());
        
        return result;
    }
    

    /**
     * 정합성체크이후  비즈니스 로직 수행(code template)
     *
     * @param exchange
     * @return
     */    
    public Response executeCode(HttpServletRequest request, ServiceFormVO serviceForm, Map<String, Object> jsonDataParam, String actCode) {
    	log.info("TaskService:executeCode started.");
    	
    	// 업무 처리 결과 Response
    	Response response = new Response();
    	
    	try {
    		// Code Template Service 처리 Handler Class
    		ServiceHandler serviceHandler = new CodeServiceHandler();
    		
    		// action을 처리
    		response = serviceHandler.handle(request, serviceForm, jsonDataParam, actCode);
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return response;
    }
    
    
    /**
     * 모든 처리가 마무리되었을 때 호출됨.
     * 최종결과 로깅 처리함.
     *
     * @param exchange
     */
    public void complete(ServiceFormVO serviceForm, HashMap approvalData) {
        String resultCode = "";
        String resultMsg = "";
        String serviceId = "";
        
        log.info("onComplete serviceId=" + serviceId + " ResultCode=" + resultCode + " ResultMessage=" + resultMsg);
    }    
}
