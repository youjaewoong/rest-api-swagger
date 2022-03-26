package com.example.restfulwebservice.request;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/request")
public class RequestController {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@GetMapping("/index")
	public String indxe(RequestListUsers message) throws Exception {
		return "request/request";
	}
	
	@ResponseBody
	@PostMapping("/list-user")
	public String listUser( RequestListUsers request) throws Exception {
		return objectMapper.writeValueAsString(request);
	}
	
	@ResponseBody
	@PostMapping("/list-map")
	public String listMap( RequestListMap request) throws Exception {
		return objectMapper.writeValueAsString(request);
	}
	
	@ResponseBody
	@PostMapping("/list-string")
	public String listString(RequestListString request) throws Exception {
		return objectMapper.writeValueAsString(request);
	}
	
	@ResponseBody
	@PostMapping("/map")
	public String map(@RequestParam Map<String, Object> request) throws Exception {
		return objectMapper.writeValueAsString(request);
	}
	
	@ResponseBody
	@PostMapping("/json")
	public String json(@RequestBody RequestListJson request) throws Exception {
		return objectMapper.writeValueAsString(request);
	}
}
