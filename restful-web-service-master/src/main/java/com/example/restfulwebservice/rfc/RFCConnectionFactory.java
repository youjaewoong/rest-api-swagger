package com.example.restfulwebservice.rfc;

import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;

public class RFCConnectionFactory {
	
	private JCoRepository repository;
	private JCoDestination destination;
	
	public RFCConnectionFactory(String poolName) {
		try {
			destination= JCoDestinationManager.getDestination(poolName);
			destination.ping();
			repository = destination.getRepository();
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	public JCoFunction getFunction(String functionStr) {
		JCoFunction function = null;
		try {
			function = repository.getFunction(functionStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Problem retrieving JCO.Function object.");
		}
		if(function == null)
			throw new RuntimeException("Not possible to receive function. ");
	
		return function;
	}
	
	public void execute(JCoFunction function) {
		try {
			JCoContext.begin(destination);
			function.execute(destination);
			JCoContext.end(destination);
		} catch (JCoException e) {
			e.printStackTrace();
		}
	}
}
