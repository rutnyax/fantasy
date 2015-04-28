package com.kossyuzokwe.fantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUserId(String id);

	User findByUserName(String username);
	
	User findByUserEmail(String email);
	
	void delete(User user);

}
