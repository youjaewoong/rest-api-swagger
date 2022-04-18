package com.example.restfulwebservice.builder;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.restfulwebservice.builder.vo.ServiceFormVO;


public interface ServiceHandler {

	 /**
     * 비즈니스로직 처리.
     * @param exchange
     * @param serviceContext
     * @param bpmHandler
     * @return
     */
	Response handle(HttpServletRequest request, ServiceFormVO serviceForm, Map<String, Object> jsonDataParam,
			String actCode) throws Exception;
}
