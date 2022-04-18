package com.example.restfulwebservice.builder.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestVO extends SearchCommon {
	
	String companyCode;
	String requestId;
	String requestSeq;
	String requestDate;
	String serviceId;
	String bizCode;
	String statusCode;
	String statusName;
	String statusDtlCode;
	String statusDtlName;
	String approvalNo;

	String applicantUserId;
	String applicantUserName;
	String applicantPosCode;
	String applicantPosName;
	String applicantDeptCode;
	String applicantDeptName;
	
	String targetUserId;
	String targetUserName;
	String targetPosCode;
	String targetPosName;	
	String targetDeptCode;
	String targetDeptName;
	String targetWorkplaceCode;
	String targetWorkplaceName;
	String targetExtno;

	String createUser;
	String createTime;
	String updateUser;
	String updateTime;

	String applicantCreateFrom;
    String applicantCreateTo;
	String serviceDisplayName;
	String isCharge;
	String isSupport;
	
	String userId;
	String selectAuthType;
	
	String requestRowId;
	String menuNo;

	String statusKeyword;
}
