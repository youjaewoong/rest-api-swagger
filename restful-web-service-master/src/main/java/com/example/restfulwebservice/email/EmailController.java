package com.example.restfulwebservice.email;


import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class EmailController {

   @Autowired
   EmailService emailService; 
   
   @Autowired
   EmailSender customMailSender;
   
   @GetMapping("/email/send-0")
   @ApiOperation(value="이메일 발송", notes="이메일 발송을 한다.")
   public void send0(@Valid EmailTemplateRequest emailTemplateRequest) throws MessagingException, IOException {
	  emailService.sendMail(emailTemplateRequest);
   }
   @GetMapping("/email/send-1")
   @ApiOperation(value="이메일 발송", notes="이메일 발송을 한다.")
   public void send1(@Valid EmailTemplateRequest emailTemplateRequest) throws MessagingException, IOException {
	  emailService.sendMail(emailTemplateRequest,new Template01());
   }
   @GetMapping("/email/send-2")
   @ApiOperation(value="이메일 발송", notes="이메일 발송을 한다.")
   public void send2(@Valid EmailTemplateRequest emailTemplateRequest) throws MessagingException, IOException {
	  emailService.sendMail(emailTemplateRequest,new Template02());
   }
   @GetMapping("/email/send-3")
   @ApiOperation(value="이메일 발송", notes="이메일 발송을 한다.")
   public void send3(@Valid EmailTemplateRequest emailTemplateRequest) throws MessagingException, IOException {
	  emailService.sendMail(emailTemplateRequest,new Template03());
   }
   @GetMapping("/email/send-4")
   @ApiOperation(value="이메일 발송", notes="이메일 발송을 한다.")
   public void send4(@Valid EmailTemplateRequest emailTemplateRequest) throws MessagingException, IOException {
	  emailService.sendMail(emailTemplateRequest,new Template04());
   }
}