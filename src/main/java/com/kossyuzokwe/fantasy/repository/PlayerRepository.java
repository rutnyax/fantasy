package com.kossyuzokwe.fantasy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.entity.Player;
import com.kossyuzokwe.fantasy.entity.Team;

public interface PlayerRepository extends JpaRepository<Player, String>{

	List<Player> findByTeam(Team team, Pageable pageable);
}
