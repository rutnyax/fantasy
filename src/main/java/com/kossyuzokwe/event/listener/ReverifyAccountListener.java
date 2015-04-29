package com.kossyuzokwe.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;

import com.kossyuzokwe.event.OnReverifyAccountEvent;
import com.kossyuzokwe.fantasy.dao.VerificationTokenRepository;
import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.model.VerificationToken;
import com.kossyuzokwe.fantasy.service.EmailService;
import com.kossyuzokwe.fantasy.service.UserService;

public class ReverifyAccountListener implements ApplicationListener<OnReverifyAccountEvent> {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public void onApplicationEvent(OnReverifyAccountEvent event) {
		User user = event.getUser();
		VerificationToken existingToken = verificationTokenRepository.findByUser(user);
		String token = existingToken.getToken();
		VerificationToken newToken = userService.regenerateVerificationToken(token);
		SimpleMailMessage email = emailService.constructVerifyEmailMessage(event, user, newToken.getToken());
		emailService.send(email);
	}

}
