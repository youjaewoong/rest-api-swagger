package com.example.restfulwebservice.api.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rest-template/api")
public class RestTemplateController {

	@Autowired
    @Qualifier("objectMapper")
    private ObjectMapper objectMapper;
	
	@Autowired()
	@Qualifier("customRestTemplate")
	private RestTemplate restTemplate;
	
	
	private static String TEST_JSON_URL = "https://jsonplaceholder.typicode.com/posts";
	
	
    @GetMapping("/get")
    public ResponseEntity<String> get() {
        return restTemplate.getForEntity(TEST_JSON_URL, String.class);
    }
    
    
    @GetMapping("/get/{id}")
    public ResponseEntity<String> getRoute(@PathVariable String id) {
        return restTemplate.getForEntity(TEST_JSON_URL+"/{id}", String.class, id);
    }
    
    
    @PostMapping("/posts")
    public ResponseEntity<String> posts() {
        return restTemplate.postForEntity(TEST_JSON_URL+"/", null, String.class);
    }
    
}
