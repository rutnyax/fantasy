package com.kossyuzokwe.fantasy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.jpa.LeagueRepository;
import com.kossyuzokwe.fantasy.jpa.PlayerRepository;
import com.kossyuzokwe.fantasy.jpa.TeamRepository;
import com.kossyuzokwe.fantasy.jpa.UserRepository;

@Service
public class LeagueService {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private UserRepository userRepository;

	public void delete(String id) {
		leagueRepository.delete(id);
	}

}
