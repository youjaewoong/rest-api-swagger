package com.example.restfulwebservice.rfc;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rfc")
public class RfcController {

    @PostMapping("/call")
    public RFCServiceManager getRfcResultData(@RequestBody RfcRequest rfcRequest) throws Exception {
    	
    	RFCServiceManager rfcManager = new RfcClient("ZH_FI_IM_IF_FMDOC", 
    			rfcRequest.getParams(), 
    			rfcRequest.getTables()).getResultManager();
    	
    	rfcManager.getValue("E_ZCFMDOC"); //성공 리턴값
    	rfcManager.getValue("E_ZNITEMNO");//성공 리턴값
    	rfcManager.getValue("E_SUBRC"); // 0:성공, 9:실패
    	return rfcManager;
    }
}