package com.kossyuzokwe.fantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.Player;

public interface PlayerRepository extends JpaRepository<Player, String>{

	Player findByPlayerName(String playerNames);
}
