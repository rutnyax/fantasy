package com.kossyuzokwe.fantasy.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.jpa.LeagueRepository;
import com.kossyuzokwe.fantasy.jpa.PlayerRepository;
import com.kossyuzokwe.fantasy.jpa.TeamRepository;
import com.kossyuzokwe.fantasy.jpa.UserRepository;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;

@Service
public class TeamService {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private UserRepository userRepository;

	public void save(Team team, String name) {
		User user = userRepository.findByUserName(name);
		team.setUser(user);
		teamRepository.save(team);
	}

	@PreAuthorize("#team.user.userName == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("team") Team team) {
		teamRepository.delete(team);
	}
/*
	@PreAuthorize("#team.user.userName == authentication.name or hasRole('ROLE_ADMIN')")
	public void update(@P("team") Team team) {
		Team oldTeam = teamRepository.findOne(team.getTeamId());
		
		teamRepository.delete(team);
	}
*/
	public Team findOne(String id) {
		return teamRepository.findOne(id);
	}

	public List<Team> findAll() {
		return teamRepository.findAll();
	}
}
