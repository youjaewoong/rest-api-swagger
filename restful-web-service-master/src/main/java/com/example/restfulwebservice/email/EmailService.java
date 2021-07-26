package com.example.restfulwebservice.email;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	public void sendMail(Map<String, Object> emailTemplateRequest, EmailTemplateStrategy emailTemplateStrategy) throws MessagingException, IOException;

	String setTemplateHtml(Map<String, Object> emailTemplateReques,EmailTemplateStrategy templateClass);

	
}