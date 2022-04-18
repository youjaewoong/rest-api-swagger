package com.example.restfulwebservice.builder.util;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class AjaxResponseUtil {

	public static ResponseEntity<String> getResponseEntity(Object obj) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		if (obj == null) {
			return new ResponseEntity<String>("", responseHeaders, HttpStatus.CREATED);
		}
		String jsonString = AjaxResponseUtil.getObjectToJsonString(obj);

		return new ResponseEntity<String>(jsonString, responseHeaders, HttpStatus.CREATED);
	}


	public static String getObjectToJsonString(Object obj) {
	    
		String jsonString;
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		if (obj instanceof List) {
			JSONArray jsonArray = JSONArray.fromObject(obj, jsonConfig);
			jsonString = jsonArray.toString();
		} else {
			JSONObject jsonObject = JSONObject.fromObject(obj, jsonConfig);
			jsonString = jsonObject.toString();
		}
		return jsonString;
	}
}
