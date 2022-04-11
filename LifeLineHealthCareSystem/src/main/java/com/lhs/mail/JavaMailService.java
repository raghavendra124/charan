package com.lhs.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class JavaMailService {

	@Autowired
	JavaMailSender javaMailSender;

	public void generateMail(String to, String sub, String body) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom("charanratnala98@gmail.com");
		mailMessage.setTo(to);
		mailMessage.setSubject(sub);
		mailMessage.setText(body);

		javaMailSender.send(mailMessage);

	}
	
	
	
	
	
	
public	void generateMailWithAttachment(String toMail,String sub,String body) throws MessagingException
	{
		
		MimeMessage message= javaMailSender.createMimeMessage();
		MimeMessageHelper mes = new MimeMessageHelper(message, true);
		//MimeMessageHelper mes= new MimeMessageHelper(message);
	try {
		mes.setTo(toMail);
		mes.setFrom("charanratnala98@gmail.com");
		mes.setSubject(sub);
		mes.setText(body);
		//zmes.setTo(InternetAddress.parse("email1@test.com,email2@test.com")); 
//		FileSystemResource r= new FileSystemResource(new File(attachment));
//		
//		
//	mes.addAttachment(r.getFilename(), r);
		javaMailSender.send(message);
		
				
	}
	catch(MessagingException e)
	{
		e.printStackTrace();
	}

}
}
