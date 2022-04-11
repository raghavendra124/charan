package com.lhs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lhs.mail.JavaMailService;

@SpringBootApplication
public class LifeLineHealthCareSystemApplication {

	@Autowired
	JavaMailService serv;
	public static void main(String[] args) {
		SpringApplication.run(LifeLineHealthCareSystemApplication.class, args);}

	
		

	

	@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}

}
