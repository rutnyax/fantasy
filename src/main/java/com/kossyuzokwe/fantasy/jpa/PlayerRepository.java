package com.kossyuzokwe.fantasy.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.Player;
import com.kossyuzokwe.fantasy.model.Team;

public interface PlayerRepository extends JpaRepository<Player, String>{

	List<Player> findByTeam(Team team, Pageable pageable);
}
