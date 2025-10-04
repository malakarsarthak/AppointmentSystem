package com.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailServices 
{
	@Autowired
	private JavaMailSender mailSender;
	
	public void userSaveMailSender(String name, String email, String pwd) throws MessagingException
	{
		final String msg = "<h1> Hello"+name+"</h1> <hr>"
	    		+"<p> Welcom " + name +",Your registeration in done.Your login and Password is mention below."
	    		+"</p><br>"
	    		+"<h4>Password :"+ pwd +"</h4>"
	    		+"<b>Please change the password as soon as possible.</b><br><br>"
	    		+"Thanks.."
                +"<hr>";
		
	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	    helper.setFrom("dhundri22@gmail.com");
	    helper.setTo(email);
	    helper.setSubject("Account Register Done..");
	    helper.setText(msg, true); // true = enable HTML

	    mailSender.send(mimeMessage);
	}
}
