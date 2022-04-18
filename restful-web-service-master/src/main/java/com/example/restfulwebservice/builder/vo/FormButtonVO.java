package com.example.restfulwebservice.builder.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormButtonVO extends SearchCommon {

    private String companyCode;
    private String formId;
    private String roleId;
    private String buttonCode;
    private String buttonName;
    private String useAt;
    private String createUser;
    private String updateUser;
    
    private String isSupport;
    private String isCharge;
    
    private String languageCode;
    
}
