package com.example.restfulwebservice.request;

import java.util.List;

import lombok.Data;

@Data
public class RequestListUsers {
	private List<User> users;
	
	@Data
	public static class User {
		private String name;
		private String email;
	}
}
