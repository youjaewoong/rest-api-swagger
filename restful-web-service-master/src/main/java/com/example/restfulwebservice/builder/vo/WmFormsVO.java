package com.example.restfulwebservice.builder.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WmFormsVO {
    
    private String companyCode;

    private String formId;

    private String formName;

    private String formDesc;

    private String revision;
    
    private String formTmplId;

    private String locatedPath;

    private String html;

    private String json;
    
    private String originJson;
    
    private String jscript;

    private String createUser;

    private String updateUser;
}
