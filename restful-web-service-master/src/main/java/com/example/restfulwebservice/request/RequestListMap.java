package com.example.restfulwebservice.request;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RequestListMap {
	
	private List<userInfo> users;
	
	@Data
	public static class userInfo {
		private Map<String ,Object> names;
		private Map<String ,Object> emails;
	}
}
