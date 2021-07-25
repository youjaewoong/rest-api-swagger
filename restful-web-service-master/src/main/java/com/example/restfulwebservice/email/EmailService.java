package com.example.restfulwebservice.email;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	public void sendMail(EmailTemplateRequest emailTemplateRequest) throws MessagingException, IOException;
	public void sendMail(EmailTemplateRequest emailTemplateRequest, EmailTemplateStrategy emailTemplateStrategy) throws MessagingException, IOException;

	String setTemplateHtml(EmailTemplateStrategy templateClass);

	
	
}