package com.liucm.service;

public interface MailService {
	public boolean sendSimpleMail(String to,String subject, String text);
}
