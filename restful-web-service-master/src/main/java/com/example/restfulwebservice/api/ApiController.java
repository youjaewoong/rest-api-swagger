package com.example.restfulwebservice.api;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Value("classpath:json/json-ex1.json")
	Resource jsonEx1;

	@Value("classpath:json/json-ex2.json")
	Resource jsonEx2;

	@Autowired
    @Qualifier("objectMapper")
    private ObjectMapper objectMapper;
	
	
	/**
	 * cael case json file
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
    @GetMapping("/v1")
    public ApiV1Response v1_get() throws JsonParseException, JsonMappingException, IOException {
    	
        return objectMapper.readValue(resourceToString(jsonEx1), ApiV1Response.class);
    }
    
    
    /**
     * 대문자 _ 형태의 json file
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @GetMapping("/v2")
    public ApiV2Response v2_get() throws JsonParseException, JsonMappingException, IOException {
    	
    	return objectMapper.readValue(resourceToString(jsonEx2), ApiV2Response.class);
    }
    
    
    /**
     * cael case json file
     * @param apiV2Reuqest
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @PostMapping("/v1")
    public ApiV1Response v1_post(@RequestBody ApiV2Reuqest apiV2Reuqest) throws JsonParseException, JsonMappingException, IOException {
    	
    	return objectMapper.readValue(resourceToString(jsonEx1), ApiV1Response.class);
    }
    
    
    /**
     * 대문자 _ 형태의 json file
     * @param apiV2Reuqest
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @PostMapping("/v2")
    public ApiV2Response v2_post(@RequestBody ApiV2Reuqest apiV2Reuqest) throws JsonParseException, JsonMappingException, IOException {
    	
    	ApiV2Response apiV2Response = objectMapper.readValue(resourceToString(jsonEx2), ApiV2Response.class);
    	
    	if (apiV2Reuqest.getId().equals(apiV2Response.getId())) {
    		apiV2Response.setResultCode("00");
    	} else {
    		apiV2Response.setResultCode("-1");
    	}
    	return apiV2Response;
    }

    
	private String resourceToString(Resource resource) throws IOException {
		return new String(Files.readAllBytes(resource.getFile().toPath()));
	}

}
