package com.kossyuzokwe.fantasy.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.dao.LeagueRepository;
import com.kossyuzokwe.fantasy.dao.PlayerRepository;
import com.kossyuzokwe.fantasy.dao.TeamRepository;
import com.kossyuzokwe.fantasy.dao.UserRepository;
import com.kossyuzokwe.fantasy.model.Player;

@Service
public class PlayerService {

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}
}
