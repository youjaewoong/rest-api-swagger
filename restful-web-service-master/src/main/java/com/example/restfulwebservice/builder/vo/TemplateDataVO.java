package com.example.restfulwebservice.builder.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TemplateDataVO extends SearchCommon {
	
	private String tableName;
	
	private Map<String,Object> columnNames;
	private Map<String,Object> columnValues;
	
	private String companyCode;
	private String requestId;
	private String requestSeq;
	private String requestRowId;
	
	private String serviceId;
	private String bizCode;
	
	private String isCharge="N";
	private String isSupport="N";
	private String isCorp="N";
	private String userId;

	private ArrayList<Map<String,Object>> whereValues;
	private ArrayList<ServiceRequestVO> whereValuesVo;
	
	// 통합이력 검색용 
	private Map<String,Object> serviceIds;
	private Map<String,Object> bizCodes;
	
	private String requestStartYmd;
	private String requestEndYmd;
	private String targetUserId;
	private String targetDeptCode;	
	private String targetWorkplaceCode;
	private String targetPosCode;
	private String applicantUserId;
	private String subDivision;

	// 코드템플릿 기간검색용
	private String columnValue;
	private String startYmd;
	private String endYmd;
	
	// 코드템플릿 상태조건
	private ArrayList<String> searchStatusCode;
	
	
	private String createUser;
	private String updateUser;
	
	// 전체상세보기 조건
	private String selectAuthType;
	private String applicantCreateFrom;
	private String applicantCreateTo;
	private String statusCode;
	private String statusDtlCode;
}
