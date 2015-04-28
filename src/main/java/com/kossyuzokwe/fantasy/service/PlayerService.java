package com.kossyuzokwe.fantasy.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.dao.LeagueRepository;
import com.kossyuzokwe.fantasy.dao.PlayerRepository;
import com.kossyuzokwe.fantasy.dao.TeamRepository;
import com.kossyuzokwe.fantasy.dao.UserRepository;
import com.kossyuzokwe.fantasy.model.Player;
import com.kossyuzokwe.fantasy.util.Constants;

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

	public Collection<Player> getPlayers() {
		return playerRepository.findAll(new PageRequest(0, Constants.STANDARD_PAGE_SIZE, Direction.DESC, "playerName")).getContent();
	}
}
