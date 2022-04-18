package com.example.restfulwebservice.builder;

import java.io.Serializable;

/**
 * Created by H1701111 on 2017-03-22.
 */
public class Response implements Serializable{
	private static final long serialVersionUID = -5312249282521655876L;
	private String resultCode;
    private String resultMessage;
    private String bizCode;
    private String actCode;
    private String userId;
    
    private String resultRequestId;
    private String resultRequestRowId;
    
    public Response(String resultCode, String resultMessage, String bizCode, String actCode, String userId) {
    	this.resultCode = resultCode;
    	this.resultMessage = resultMessage;
    	this.bizCode = bizCode;
    	this.actCode = actCode;
    	this.userId = userId;
    	this.resultRequestRowId = "";
    }
    
    public Response(String bizCode, String actCode, String userId) {
        this.resultCode = "0";
        this.resultMessage = "SUCCESS";
        this.bizCode = bizCode;
        this.actCode = actCode;
        this.userId = userId;
        this.resultRequestRowId = "";
    }
    
    public Response() {
    }
    
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
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

    public String getResultRequestId() {
        return resultRequestId;
    }

    public void setResultRequestId(String resultRequestId) {
        this.resultRequestId = resultRequestId;
    }

    public String getResultRequestRowId() {
		return resultRequestRowId;
	}

	public void setResultRequestRowId(String resultRequestRowId) {
		this.resultRequestRowId = resultRequestRowId;
	}

	@Override
    public String toString() {
        return "Response{" +
                "resultCode='" + resultCode + '\'' +
                "resultMessage='" + resultMessage + '\'' +
                ", bizCode='" + bizCode + '\'' +
                ", actCode='" + actCode + '\'' +
                ", userId='" + userId + '\'' +
                ", resultRequestId='" + resultRequestId + '\'' +
                ", resultRequestRowId='" + resultRequestRowId + '\'' +
                '}';
    }
}
