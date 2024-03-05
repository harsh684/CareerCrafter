package com.hexaware.careercrafterfinal.service;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(EmailService.class);

	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Async
	public String sendEmail(String to, String subject, String body) throws Exception{
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body);
			
			logger.info("Sending Email to the applicant");
			mailSender.send(mimeMessage);
			
			return "mail sent";
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Async
	public String sendRegistrationMail(String to) throws Exception {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);

			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject("Account Registered");
			mimeMessageHelper.setText("You have Successfully Registered your accout with Career Crafter. "
					+ "Next Steps are to create Your profile. \n Thank You,\nCareer Crafter");
			
			logger.info("Sending Email to the applicant");
			mailSender.send(mimeMessage);
			
			return "mail sent";
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
}
