package com.lhs.restcontroller;

import java.security.Principal;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;
import javax.naming.directory.InvalidAttributeIdentifierException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.RegistrationRepo;
import com.lhs.entity.RegistrationEntity;
import com.lhs.mail.JavaMailService;
import com.lhs.payload.RequestEmail;
import com.lhs.payload.RequestOtp;

@RestController
@RequestMapping("/api")
public class ForgotpasswordController {

	@Autowired
	JavaMailService sender;

	@Autowired
	RegistrationRepo rep;
	int random;

	// private final LoadingCache<UUID, Integer> oneTimePasswordCache;

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
	ResponseEntity<String> getEmailByPath(@RequestBody RequestEmail email) throws MessagingException, InvalidAttributeIdentifierException {
		if (rep.existsByEmail(email.getEmail())) {
			
			RegistrationEntity ent= rep.findByEmail(email.getEmail());
			
			System.out.println(ent);
			if(email.getEmail()==ent.getEmail())
			{
			g();
			sender.generateMailWithAttachment(email.getEmail(), " <h1> mail for otp generation ",
					"For security reason, you're required to use the following !!!!! One Time Password to login ?  Your OTP "+"</h1>"
							+ random);
			
			return new ResponseEntity<String>("otp sended Successfully pls Check... ", HttpStatus.OK);}
		
			else
			{
				throw new InvalidAttributeIdentifierException("pls enter valid mail");
			}
		}

		else {
			throw new RuntimeException("email not found");
		}

	}
	

	@PostMapping("/otp/")
	private ResponseEntity<String> validateOtp(@RequestBody RequestOtp otp) throws ExecutionException {

	
		if (random == otp.getOtp()) {
			return new ResponseEntity<String>("valid ", HttpStatus.OK);
		}

		else {
			return new ResponseEntity<String>("Otp Is Invalid. Please Check ", HttpStatus.BAD_REQUEST);
		}
	}

}
