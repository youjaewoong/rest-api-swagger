package com.example.restfulwebservice.builder;

import java.util.HashMap;
import java.util.Map;


public class ValidatorFactory {
    private static ValidatorFactory ourInstance = new ValidatorFactory();
    public static ValidatorFactory getInstance() {
        return ourInstance;
    }

    private ValidatorFactory() {
    }

    private static Map<String, Validator> TEMP = new HashMap<>();
    static {
       
        //TEMP.put(Constants.BIZCODE_TONER, new TonerServiceValidator());							// 토너 서비스
    }

    public Validator getValidator(String url) {
    	return TEMP.get(url);
    }
}
