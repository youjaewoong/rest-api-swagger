package com.example.restfulwebservice.email;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.example.restfulwebservice.utils.ObjectMapperFacade;

@Service
public class EmailServiceImple implements EmailService {
 
	@Autowired
	EmailSender customMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Override
	public String setTemplateHtml(Map<String, Object> emailTemplateReques, EmailTemplateStrategy templateClass) {
		return templateClass.templateBindig(emailTemplateReques,templateEngine);
	}

	@Override
	public void sendMail(Map<String, Object> emailTemplateRequest, EmailTemplateStrategy templateClass) throws MessagingException, IOException {
		EmailRequest emailRequest = (EmailRequest) ObjectMapperFacade.convertObject(emailTemplateRequest, EmailRequest.class);
		emailRequest.setContetns(setTemplateHtml(emailTemplateRequest, templateClass));
		customMailSender.sendMail(emailRequest);
	}
}