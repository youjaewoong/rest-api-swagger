package com.example.restfulwebservice.rfc;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRecordMetaData;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;

public class RFCServiceManager {
	
	private RFCConnectionFactory connect;
	private JCoFunction function;
	private String server;
	private String poolName;
	private String endPointName;
	

	public RFCServiceManager(String server, String poolName) {
		this.server = server;
		this.poolName = poolName;
		this.endPointName = poolName + "_" + server;
		createDestConfigFile();
	}

	private void createDestConfigFile() {
		String confFileName = endPointName + ".jcoDestination";
		File confFile = new File(confFileName);
		
		if(!confFile.exists()) {
			Properties configProp = new Properties();
			
			if(server.equalsIgnoreCase("REAL")) {
				configProp.setProperty(DestinationDataProvider.JCO_ASHOST , "herppsvc.hmckmc.co.kr");
				configProp.setProperty(DestinationDataProvider.JCO_SYSNR , "20");
				configProp.setProperty(DestinationDataProvider.JCO_CLIENT , "100");
				configProp.setProperty(DestinationDataProvider.JCO_USER, "HRIF02");
				configProp.setProperty(DestinationDataProvider.JCO_PASSWD , "HKMCbc1!");
				configProp.setProperty(DestinationDataProvider.JCO_LANG , "KO");
				configProp.setProperty(DestinationDataProvider.JCO_R3NAME , "EPH");
				configProp.setProperty(DestinationDataProvider.JCO_GROUP , "HR_ESS");
				configProp.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY , "200");
				configProp.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT , "20");
			} else {
				configProp.setProperty(DestinationDataProvider.JCO_ASHOST , "herpdd00.hmckmc.co.kr");
				configProp.setProperty(DestinationDataProvider.JCO_SYSNR , "00");
				configProp.setProperty(DestinationDataProvider.JCO_CLIENT , "500");
				configProp.setProperty(DestinationDataProvider.JCO_USER, "HRIF07");
				configProp.setProperty(DestinationDataProvider.JCO_PASSWD , "HRIF07");
				configProp.setProperty(DestinationDataProvider.JCO_LANG , "KO");
				configProp.setProperty(DestinationDataProvider.JCO_R3NAME , "EPH");
				configProp.setProperty(DestinationDataProvider.JCO_GROUP , "HR_ESS");
				configProp.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY , "200");
				configProp.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT , "20");
			}
			
			try {
				FileOutputStream stream = new FileOutputStream(confFile, false);
				stream.close();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public JCoFunction setFunction(String functionName) {
		try {
			connect = RFCConnectionManager.getInstance().getConnection(endPointName);
			function = connect.getFunction(functionName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return function;
	}
	
	public void setParam(String key, Object value) {
		function.getImportParameterList().setValue(key, value);
	}
	
	public void setChangeParam(String key, Object value) {
		function.getChangingParameterList().setValue(key, value);
	}
	
	public void execute() {
		try {
			connect.execute(function);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(String name) {
		return function.getExportParameterList().getString(name);
	}
	
	public JCoStructure getStructure(String name) {
		return function.getExportParameterList().getStructure(name);
	}
	
	public JCoTable getTable(String name) {
		return function.getImportParameterList().getTable(name);
	}
	
	/**
	 * JCoStructure => Map 변환
	 */
	public Map<String, Object> setStructureToMap(JCoStructure jCoStructure) {
		JCoRecordMetaData jCoRecordMetaData = jCoStructure.getRecordMetaData();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		for(int i=0; i < jCoRecordMetaData.getFieldCount(); i++) {
			String FileName = jCoRecordMetaData.getName(i);
			String FileValue = jCoStructure.getString(FileName);
			resultMap.put(FileName, FileValue);
		}
		return resultMap;
	}

	/**
	 * JCoTable => List 변환
	 */
	public List<Map<String, Object>> setTableToList(JCoTable jcoTable) {
		JCoRecordMetaData jCoRecordMetaData = jcoTable.getRecordMetaData();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i < jcoTable.getNumRows(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			jcoTable.setRow(i);
			for(int k=0; k < jCoRecordMetaData.getFieldCount(); k++) {
				String FileName = jCoRecordMetaData.getName(i);
				String FileValue = jcoTable.getString(FileName);
				map.put(FileName, FileValue);
			}
			resultList.add(map);
		}
		return resultList;
	}
}