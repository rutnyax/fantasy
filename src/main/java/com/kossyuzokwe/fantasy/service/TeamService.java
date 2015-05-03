package com.kossyuzokwe.fantasy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.dao.LeagueRepository;
import com.kossyuzokwe.fantasy.dao.TeamRepository;
import com.kossyuzokwe.fantasy.dao.UserRepository;
import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;

@Service
public class TeamService {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Team createTeam(Team team, String username, String leagueId) {
		User user = userRepository.findByUserName(username);
		team.setUser(user);
		League league = leagueRepository.findByIdFetchTeams(leagueId);
		List<Team> teams = league.getTeams();
		teams.add(team);
		league.setTeams(teams);
		league = leagueRepository.save(league);
		team.setLeague(league);
		return teamRepository.save(team);
	}

	@Transactional
	public void delete(String id) {
		teamRepository.delete(id);
	}

	@Transactional
	public Team getById(String id) {
		return teamRepository.findOne(id);
	}

	@Transactional
	public Team getByIdWithPlayers(String id) {
		return teamRepository.findByTeamIdFetchPlayers(id);
	}

	@Transactional
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	@Transactional
	public List<Team> getByUserWithPlayers(User user) {
		return teamRepository.findByUserFetchPlayers(user.getUserId());
	}

	@Transactional
	public List<Team> getByUser(User user) {
		return teamRepository.findByUser(user);
	}

	@Transactional
	public boolean isOwner(User user, Team team) {
		return team.getUser().getUserId().equals(user.getUserId());
	}

	@Transactional
	public Team updateTeam(String id, Team team) {
		Team existingTeam = teamRepository.findByTeamId(id);
		existingTeam.setTeamName(team.getTeamName());
		return teamRepository.save(existingTeam);
	}

	@Transactional
	public boolean isAvailable(String teamName, String leagueName) {
		League league = leagueRepository.findByNameFetchTeams(leagueName);
		return league.getTeams().stream().noneMatch(team -> team.getTeamName().equals(teamName));
	}
}
