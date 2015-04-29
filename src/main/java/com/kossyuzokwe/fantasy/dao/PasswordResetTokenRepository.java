package com.kossyuzokwe.fantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.PasswordResetToken;
import com.kossyuzokwe.fantasy.model.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String>{

	PasswordResetToken findByToken(String token);

	PasswordResetToken findByUser(User user);
}
