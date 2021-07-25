package com.example.restfulwebservice.email;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

@Service
public class EmailServiceImple implements EmailService {
 
	@Autowired
	EmailSender customMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	private EmailTemplateRequest emailTemplateRequest;
	private EmailTemplateStrategy emailTemplateStrategy;

	public void setEmailTemplateStrategy(EmailTemplateStrategy _emailTemplateStrategy) {
		this.emailTemplateStrategy = _emailTemplateStrategy;
	}
	
	@Override
	public String setTemplateHtml(EmailTemplateStrategy templateClass) {
		setEmailTemplateStrategy(templateClass);
		return this.emailTemplateStrategy.templateBindig(emailTemplateRequest,templateEngine);
	}

	@Override
	public void sendMail(EmailTemplateRequest _emailTemplateRequest) throws MessagingException, IOException {
		this.emailTemplateRequest = _emailTemplateRequest;
		
		EmailRequest emailRequest = _emailTemplateRequest;
		emailRequest.setContetns(setTemplateHtml(new Template01()));
		
		customMailSender.sendMail(emailRequest);
	}

	@Override
	public void sendMail(EmailTemplateRequest _emailTemplateRequest,EmailTemplateStrategy templateClass) throws MessagingException, IOException {
		this.emailTemplateRequest = _emailTemplateRequest;
		
		EmailRequest emailRequest = _emailTemplateRequest;
		emailRequest.setContetns(setTemplateHtml(templateClass));
		
		customMailSender.sendMail(emailRequest);
	}
}