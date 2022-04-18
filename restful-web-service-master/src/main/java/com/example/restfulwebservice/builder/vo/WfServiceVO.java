package com.example.restfulwebservice.builder.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfServiceVO {
   
    private String companyCode;
    
    private String serviceId;
    
    private String bizCode;
    
    private String serviceName;
    
    private String serviceTypeCode;
    
    private String formId;
    
    private String serviceUrl;
    
    private String preProcessUrl;
    
    private String serviceDisplayName;
    
    private String serviceDisplayNameEng;
    
    private String motoName;
    
    private String motoPosition;
    
    private String ciDisplayAt;
    
    private String chargeApprovalAt;
    
    private String managerApprovalAt;
    
    private String approvalDecisionCode;
    
    private String signDisplayType;
    
    private int signSendCount = 0;
    
    private int signReceiveCount = 0;
    
    private String printAt;
    
    private String personinfoList;
    
    private String acceptMailAt;
    
    private String acceptMailAddress;
    
    private String guideBoxContents;
    
    private String noticePopupAt;
    
    private String privateinfoAgreementAt;
    
    private String dbHost;
    
    private String dbSchema;
    
    private String dbTable;
    
    private String roleCharge;
    
    private String roleSupport;
    
    private String useAt;
    
    private String listUseAt;
    
    private String createUser;
    
    private String updateUser;

    private String docExpireYear;
    
    private String roleUserUseAt;
    
    private String roleSupportUseAt;
    
    private String retirerSepratAt;
    
    private String linkServiceId;
    
}
