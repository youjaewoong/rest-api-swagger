package com.restapi.email;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

public interface EmailTemplateStrategy {
	
	public String templateBindig(Map<String, Object> emailTemplateRequest, TemplateEngine templateEngine);
	
	final String FOLD_NAME = "email";
	String TYPE_KO = "Ko";
	
	/**
	 * template 데이터를 바인딩한다.
	 * @param emailTemplateRequest
	 * @return
	 */
	default Context templateContext(Map<String, Object> emailTemplateRequest) {
		Context context = new Context();
	    context.setVariables(emailTemplateRequest);
		return context;
	}
	
	/**
	 * class 이름을 template명으로 변경한다.
	 * @param object
	 * @return
	 */
	default String templatePath(Object object) {
		if(object == null) object = TYPE_KO;
		
		String path = this.getClass().getSimpleName().toLowerCase();
		return FOLD_NAME + "/" + path+object;
	}
}

class Template01 implements EmailTemplateStrategy{
	public String templateBindig(Map<String, Object> emailTemplateRequest, TemplateEngine templateEngine) {
		return templateEngine.process(templatePath(emailTemplateRequest.getOrDefault("multilingual", TYPE_KO)),templateContext(emailTemplateRequest));
	}
}
class Template02 implements EmailTemplateStrategy{
	public String templateBindig(Map<String, Object> emailTemplateRequest, TemplateEngine templateEngine) {
		return templateEngine.process(templatePath(emailTemplateRequest.getOrDefault("multilingual", TYPE_KO)),templateContext(emailTemplateRequest));
	}
}
class Template03 implements EmailTemplateStrategy{
	public String templateBindig(Map<String, Object> emailTemplateRequest, TemplateEngine templateEngine) {
		return templateEngine.process(templatePath(emailTemplateRequest.getOrDefault("multilingual", TYPE_KO)),templateContext(emailTemplateRequest));
	}
}
class Template04 implements EmailTemplateStrategy{
	public String templateBindig(Map<String, Object> emailTemplateRequest, TemplateEngine templateEngine) {
		return templateEngine.process(templatePath(emailTemplateRequest.getOrDefault("multilingual", TYPE_KO)),templateContext(emailTemplateRequest));
	}
}
