package com.kossyuzokwe.fantasy.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;

public interface TeamRepository extends JpaRepository<Team, String>{

	Collection<Team> findByUser(User user, Pageable pageable);
}
