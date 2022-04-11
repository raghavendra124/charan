package com.lhs.restcontroller;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.RegistrationRepo;
import com.lhs.mail.JavaMailService;

@RestController
@RequestMapping("/api")
public class ForgotpasswordController {

	@Autowired
	JavaMailService sender;

	@Autowired
	RegistrationRepo rep;
	int random;
	//private final LoadingCache<UUID, Integer> oneTimePasswordCache;

	@GetMapping("/forgotpassword")
	public ResponseEntity<String> getResponse() {
		System.out.println(random);
		return new ResponseEntity<String>("valid .......given permission to enter email", HttpStatus.OK);

	}

	@GetMapping("/otpgeneration")
	ResponseEntity<String> g() {
		Random r = new Random();
		int rs = r.nextInt(10000);

		if (rs > 1000) {

			random = rs + 1000;

			System.out.println(random);
		}
		return new ResponseEntity<String>(" otp generated successfully " + random, HttpStatus.ACCEPTED);
	}

	// @RequestParam("getemail") String email

	@PostMapping("/getemail/")
	ResponseEntity<String> getEmailByPath( @RequestParam("email") String email
) throws MessagingException {
		if (rep.existsByEmail(email)) {		
			g();
			sender.generateMailWithAttachment("charanratnala98@gmail.com", "mail for otp generation ","For security reason, you're required to use the following !!!!! One Time Password to login ?  Your OTP "+
					random	);

			return new ResponseEntity<String>("otp sended Successfully pls Check... ", HttpStatus.OK);
		}

		else {
			throw new RuntimeException("email not found");
		}

	}
	
	@PostMapping("/otp/{ot}")
	private ResponseEntity<String> validateOtp( @PathVariable(value="ot") int otp) throws ExecutionException {
		
		
		
		if(random==otp)
		{
			return new ResponseEntity<String>("valid ", HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity<String>("Invalid ", HttpStatus.BAD_REQUEST);		}
	}
	
	
	
	
}


