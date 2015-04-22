package com.kossyuzokwe.fantasy.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.jpa.LeagueRepository;
import com.kossyuzokwe.fantasy.jpa.PlayerRepository;
import com.kossyuzokwe.fantasy.jpa.RoleRepository;
import com.kossyuzokwe.fantasy.jpa.TeamRepository;
import com.kossyuzokwe.fantasy.jpa.UserRepository;
import com.kossyuzokwe.fantasy.model.Player;
import com.kossyuzokwe.fantasy.model.Role;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.util.Constants;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOneById(String id) {
		return userRepository.findOne(id);
	}

	public User findOneWithTeamsById(String id) {
		User user = findOneById(id);
		List<Team> teams = teamRepository.findByUser(user, new PageRequest(0,
				Constants.STANDARD_PAGE_SIZE, Direction.DESC, "teamId"));
		for (Team team : teams) {
			List<Player> players = playerRepository.findByTeam(team,
					new PageRequest(0, Constants.STANDARD_PAGE_SIZE,
							Direction.DESC, "playerId"));
			team.setPlayers(players);
		}
		user.setTeams(teams);
		return user;
	}

	public void save(User user) {
		user.setUserEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setUserPassword(encoder.encode(user.getUserPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByRoleName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}

	public User findOneWithTeamsByName(String username) {
		User user = userRepository.findByUserName(username);
		return findOneWithTeamsById(user.getUserId());
	}

	public void delete(String id) {
		userRepository.delete(id);
	}
	
	public User findOneByUserName(String username) {
		return userRepository.findByUserName(username);
	}
}
