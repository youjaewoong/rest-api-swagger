package com.example.restfulwebservice.builder;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.example.restfulwebservice.builder.vo.ServiceFormVO;
import com.example.restfulwebservice.builder.vo.ValidationVO;

public interface Validator {

	ValidationVO validate(HttpServletRequest request, ServiceFormVO serviceForm, HashMap<String, String> approvalData, String actCode) throws Exception;
}
