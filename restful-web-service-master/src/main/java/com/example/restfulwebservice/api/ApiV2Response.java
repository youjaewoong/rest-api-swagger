package com.example.restfulwebservice.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ApiV2Response {
	@JsonProperty("ID")
    private Long id;
	
	@JsonProperty("NAME")
    private String name;
	
	@JsonProperty("EMAIL")
    private String email;
	
	@JsonProperty("PHONE")
    private String phone;
	
	@JsonProperty("AGE")
    private int age;
	
	@JsonProperty("PROJECTS")
    private String[] projects;
	
	@JsonProperty("ADDRESS")
    private Address address;
	
	@JsonProperty("PAYMENT_METHODS")
    private List<String> paymentMethods;
	
	@JsonProperty("PROFILE_INFO")
    private Map<String, Object> profileInfo;

	@JsonProperty("MEMBER_LIST")
	private Member memberList;
	
	@JsonProperty("RESULT_CODE")
	private String resultCode;
    
	@Data
	private static class Address {
		@JsonProperty("STREET")
		private String street;
		
		@JsonProperty("CITY")
		private String city;
		
		@JsonProperty("ZIP_CODE")
		private int zipcode;
		
		@JsonProperty("COUNTRY")
		private String country;
	}
	
    @Data
    private static class Member {
    	@JsonProperty("TOTAL")
    	private Long total;
    	
    	@JsonProperty("MEMBERS")
        private List<Members> members;
    	
    }
    
    @Data
    private static class Members {
    	@JsonProperty("NAME")
    	private String name;
    	
    	@JsonProperty("AGE")
    	private Long age;
    	
    	@JsonProperty("SECRETIDENTITY")
    	private String secretidEntity;
    	
    	@JsonProperty("POWERS")
    	private List<String> powers;
    	
    }
}
