package com.kossyuzokwe.fantasy.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.User;

public interface UserRepository extends JpaRepository<User, String>{

	User findByUserName(String username);

}
