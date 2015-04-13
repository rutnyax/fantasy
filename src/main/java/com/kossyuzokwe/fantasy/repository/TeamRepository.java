package com.kossyuzokwe.fantasy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.entity.Team;
import com.kossyuzokwe.fantasy.entity.User;

public interface TeamRepository extends JpaRepository<Team, String>{

	List<Team> findByUser(User user, Pageable pageable);
}
