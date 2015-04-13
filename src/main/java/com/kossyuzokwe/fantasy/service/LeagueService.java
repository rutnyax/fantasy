package com.kossyuzokwe.fantasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.repository.LeagueRepository;
import com.kossyuzokwe.fantasy.repository.PlayerRepository;
import com.kossyuzokwe.fantasy.repository.TeamRepository;
import com.kossyuzokwe.fantasy.repository.UserRepository;

@Service
public class LeagueService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private UserRepository userRepository;

}
