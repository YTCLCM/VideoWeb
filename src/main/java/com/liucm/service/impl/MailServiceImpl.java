package com.liucm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.liucm.config.MailProperties;
import com.liucm.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MailProperties mailProperties;

	@Override
	public boolean sendSimpleMail(String to,String subject, String text) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailProperties.setTo(to);

		mailMessage.setFrom(mailProperties.getFrom());
		mailMessage.setTo(mailProperties.getTo());

		mailMessage.setSubject(subject);
		mailMessage.setText(text);
		try {
			javaMailSender.send(mailMessage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
