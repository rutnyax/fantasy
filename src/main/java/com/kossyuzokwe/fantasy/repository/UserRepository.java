package com.kossyuzokwe.fantasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
