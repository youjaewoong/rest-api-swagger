package com.example.restfulwebservice.rfc;

import java.util.Map;

import lombok.Data;

@Data
public class RfcRequest {
	
	private Map<String, Object> params;
	private Map<String, Object> tables;
}