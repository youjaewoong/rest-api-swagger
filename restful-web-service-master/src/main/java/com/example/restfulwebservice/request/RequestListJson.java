package com.example.restfulwebservice.request;

import java.util.List;

import lombok.Data;

@Data
public class RequestListJson {
	private List<User> users;
	
	@Data
	public static class User {
		private String name;
		private String email;
		private List<Addr> addrs;
	}
	
	@Data
	public static class Addr {
		private String hp;
		private String add;
	}
}
