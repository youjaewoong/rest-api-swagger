package com.example.restfulwebservice.utils;


import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFacade {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertMap(Object vo) {
		 return new ObjectMapper().convertValue(vo, Map.class);
	}
	
	public static Object convertObject(Object vo, Class<?> clas) {
		return new ObjectMapper()
				   .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				   .convertValue(vo, clas);
	}
}
