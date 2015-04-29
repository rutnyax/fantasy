package com.kossyuzokwe.fantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String>{

	VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);
}
