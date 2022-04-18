package com.example.restfulwebservice.builder.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WmFormFieldVO {
    
    private String companyCode;

    private String formId;

    private String fieldId;

    private String fieldName;
    
    private String fieldType;

    private int fieldLength = 0;

    private String fieldDefault;

    private String fieldNullableAt;

    private String fieldPkAt;

    private String fieldCat;

    private String useAt;

    private int sortOrder = 0;

    private String createUser;

    private String updateUser;
}
