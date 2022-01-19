package com.example.restfulwebservice.api;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ApiV1Response {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private String[] projects;
    private Address address;
    private List<String> paymentMethods;
    private Map<String, Object> profileInfo;
    
    
    @Data
    private static class Address {
    	private String street;
        private String city;
        private int zipcode;
        private String country;
    }
}
