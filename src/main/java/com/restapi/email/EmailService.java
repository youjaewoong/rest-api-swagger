package com.restapi.email;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@Service
public interface EmailService {

	public void sendMail(Map<String, Object> emailTemplateRequest, EmailTemplateStrategy emailTemplateStrategy) throws MessagingException, IOException;

	String setTemplateHtml(Map<String, Object> emailTemplateReques,EmailTemplateStrategy templateClass);

	
}