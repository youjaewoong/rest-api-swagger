package com.restapi.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
class EmailSender {
	
    @Autowired
    private MailProperties mailProperties;
    
	public void sendMail(EmailRequest emailRequest) throws MessagingException, IOException {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setPort(mailProperties.getPort());
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setPassword(mailProperties.getPassword());
        
        InternetAddress from = new InternetAddress(mailProperties.getUsername(), "윤재웅");
        InternetAddress to = new InternetAddress(emailRequest.getEmail());
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        messageHelper.setSubject(emailRequest.getSubject());
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setText(emailRequest.getContetns(), true);
        
        //메일 보내기
        javaMailSender.send(mimeMessage);
	}
}
