package com.example.restfulwebservice.rfc;

import java.util.Map;

public class RfcClient {

	private RFCServiceManager rfcManger;

	public RfcClient(String functionName, Map<String, Object> params, Map<String, Object> tables) throws Exception {
		this.rfcManger = new RFCServiceManager("DEV", "SAP_HR");
		this.rfcManger.setFunction(functionName);
		this.setParams(params);
		this.setTables(tables);
		this.rfcManger.execute();
	}
	
	
	private void setParams(Map<String, Object> params) {
		if(params != null) {
			params.forEach((key, value) -> {
				this.rfcManger.setParam(key, value);
			});
		}
	}
	

	private void setTables(Map<String, Object> tables) {
		if(tables != null) {
			this.rfcManger.getTable("T_ITEM").appendRow();
			tables.forEach((key, value) -> {
				this.rfcManger.getTable("T_ITEM").setValue(key, value);
			});
		}
	}
	
	public RFCServiceManager getResultManager() {
		return this.rfcManger;
	}
}