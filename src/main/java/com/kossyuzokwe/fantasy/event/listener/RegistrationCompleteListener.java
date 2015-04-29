package com.kossyuzokwe.fantasy.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;

import com.kossyuzokwe.fantasy.event.OnRegistrationCompleteEvent;
import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.service.EmailService;
import com.kossyuzokwe.fantasy.service.UserService;

public class RegistrationCompleteListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		userService.createVerificationTokenForUser(user, token);
		SimpleMailMessage email = emailService.constructVerifyEmailMessage(event, user, token);
		emailService.send(email);
	}

}
