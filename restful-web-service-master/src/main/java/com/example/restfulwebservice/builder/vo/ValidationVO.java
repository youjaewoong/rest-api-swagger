package com.example.restfulwebservice.builder.vo;

public class ValidationVO {
    private String resultCode;
    private String resultMessage;
    private String serviceId;
    private String actCode;
    private String userId;

    public ValidationVO(String resultCode, String resultMessage, String serviceId, String actCode, String userId) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.serviceId = serviceId;
        this.actCode = actCode;
        this.userId = userId;
    }

    public ValidationVO(String serviceId, String actCode, String userId) {
        this.resultCode = "0";
        this.resultMessage = "SUCCESS";
        this.serviceId = serviceId;
        this.actCode = actCode;
        this.userId = userId;
    }
    
    public ValidationVO(){
        this.resultCode = "0";
        this.resultMessage = "SUCCESS";
    }


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    @Override
    public String toString() {
        return "ValidationBean{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", actCode='" + actCode + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
