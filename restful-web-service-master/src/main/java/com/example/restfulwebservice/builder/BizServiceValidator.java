package com.example.restfulwebservice.builder;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.example.restfulwebservice.builder.vo.ServiceFormVO;
import com.example.restfulwebservice.builder.vo.ValidationVO;

@Component
public class BizServiceValidator implements Validator {

    public ValidationVO validate(HttpServletRequest request, ServiceFormVO serviceForm, HashMap<String, String> approvalData, String actCode) throws Exception  {
    	
    	//valdation
        String bizCode = serviceForm.getBizCode();
        String userId = "TEST123";
        // 조회
        if(actCode.equals("search")) {

        // 저장
        }else if(actCode.equals("inser")) {
        	
        // 삭제
        }else if(actCode.equals("delete")) {

        // 수정
        }else if(actCode.equals("update")) {

            	
        }else {
        	return new ValidationVO("E199", "NO MATCH ACTION CODE", bizCode, actCode, userId);
        }
        
        return new ValidationVO(bizCode, actCode, userId);
    }
}
