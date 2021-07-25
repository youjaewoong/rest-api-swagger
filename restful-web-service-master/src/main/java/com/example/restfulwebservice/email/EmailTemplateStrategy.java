package com.example.restfulwebservice.email;

import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.databind.ObjectMapper;


public interface EmailTemplateStrategy {
	
	public String templateBindig(EmailTemplateRequest emailTemplateRequest, TemplateEngine templateEngine);
	
	final String FOLD_NAME = "email";
	String TYPE_KO = "Ko";
	
	/**
	 * template 데이터를 바인딩한다.
	 * @param emailTemplateRequest
	 * @return
	 */
	default Context templateContext(EmailTemplateRequest emailTemplateRequest) {
		Context context = new Context();
		@SuppressWarnings("unchecked")
		Map<String,Object> map = new ObjectMapper().convertValue(emailTemplateRequest, Map.class);
	    context.setVariables(map);
		return context;
	}
	
	/**
	 * class 이름을 template명으로 변경한다.
	 * @param multilingual 
	 * @return
	 */
	default String templatePath(String multilingual) {
		if(multilingual == null) multilingual = TYPE_KO;
		
		String path = this.getClass().getSimpleName().toLowerCase();
		return FOLD_NAME + "/" + path+multilingual;
	}
}

class Template01 implements EmailTemplateStrategy{
	public String templateBindig(EmailTemplateRequest emailTemplateRequest, TemplateEngine templateEngine) {
		return templateEngine.process(templatePath(emailTemplateRequest.getMultilingual()),templateContext(emailTemplateRequest));
	}
}
class Template02 implements EmailTemplateStrategy{
	public String templateBindig(EmailTemplateRequest emailTemplateRequest, TemplateEngine templateEngine) {
		return templateEngine.process(templatePath(emailTemplateRequest.getMultilingual()),templateContext(emailTemplateRequest));
	}
}
class Template03 implements EmailTemplateStrategy{
	public String templateBindig(EmailTemplateRequest emailTemplateRequest, TemplateEngine templateEngine) {
		return templateEngine.process(templatePath(emailTemplateRequest.getMultilingual()),templateContext(emailTemplateRequest));
	}
}
class Template04 implements EmailTemplateStrategy{
	public String templateBindig(EmailTemplateRequest emailTemplateRequest, TemplateEngine templateEngine) {
		return templateEngine.process(templatePath(emailTemplateRequest.getMultilingual()),templateContext(emailTemplateRequest));
	}
}
