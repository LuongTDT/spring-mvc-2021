package com.salaryspringmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmailController {
	
	@Autowired
	MailSender mailSender;
	
	/*
	 * not completed yet
	 */
	public void sendEmail(String emailFrom, String emailTo, String emailSubject, String emailContent) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(emailFrom);
		mailMessage.setTo(emailTo);
		mailMessage.setSubject(emailSubject);
		mailMessage.setText(emailContent);
		
		mailSender.send(mailMessage);
		
		
	}
	
	@RequestMapping(value = "/sendmail", method = RequestMethod.GET)
	public void userMail() {
		sendEmail("tdtl.dracula@gmail.com", "tamluong.td@gmail.com", "test send mail java", "just say hello! thanks");
	}
}
