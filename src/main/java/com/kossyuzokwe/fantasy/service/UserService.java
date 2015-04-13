package com.kossyuzokwe.fantasy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.entity.Player;
import com.kossyuzokwe.fantasy.entity.Team;
import com.kossyuzokwe.fantasy.entity.User;
import com.kossyuzokwe.fantasy.repository.LeagueRepository;
import com.kossyuzokwe.fantasy.repository.PlayerRepository;
import com.kossyuzokwe.fantasy.repository.TeamRepository;
import com.kossyuzokwe.fantasy.repository.UserRepository;
import com.kossyuzokwe.fantasy.util.Constants;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private LeagueRepository leagueRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(String id) {
		return userRepository.findOne(id);
	}

	public User findOneWithTeams(String id) {
		User user = findOne(id);
		List<Team> teams = teamRepository.findByUser(user, new PageRequest(0, Constants.STANDARD_PAGE_SIZE, Direction.DESC, "teamId"));
		for (Team team : teams) {
			List<Player> players = playerRepository.findByTeam(team, new PageRequest(0, Constants.STANDARD_PAGE_SIZE, Direction.DESC, "playerId"));
			team.setPlayers(players);
		}
		user.setTeams(teams);
		return user;
	}

	public void save(User user) {
		userRepository.save(user);
	}
}
