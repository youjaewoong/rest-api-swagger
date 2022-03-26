package com.example.restfulwebservice.rfc;

public class RFCConnectionManager {
	private static RFCConnectionManager instance = null;
	public static RFCConnectionManager getInstance() {
		if(instance == null) {
			instance = new RFCConnectionManager();
		}
		return instance;
	}
	
	public RFCConnectionFactory getConnection(String poolName) {
		RFCConnectionFactory connect = null;
		try {
			connect = new RFCConnectionFactory(poolName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return connect;
	}
}
