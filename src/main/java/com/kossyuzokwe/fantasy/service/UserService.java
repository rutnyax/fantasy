package com.kossyuzokwe.fantasy.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.dao.LeagueRepository;
import com.kossyuzokwe.fantasy.dao.PlayerRepository;
import com.kossyuzokwe.fantasy.dao.RoleRepository;
import com.kossyuzokwe.fantasy.dao.TeamRepository;
import com.kossyuzokwe.fantasy.dao.UserRepository;
import com.kossyuzokwe.fantasy.model.Player;
import com.kossyuzokwe.fantasy.model.Role;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.util.Constants;

@Service
@Transactional
public class UserService {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

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

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	public User findOneById(String id) {
		return userRepository.findOne(id);
	}

	public User findOneWithTeamsById(String id) {
		User user = findOneById(id);
		Collection<Team> teams = teamRepository.findByUser(user, new PageRequest(0,
				Constants.STANDARD_PAGE_SIZE, Direction.DESC, "teamId"));
		for (Team team : teams) {
			Collection<Player> players = playerRepository.findByTeam(team,
					new PageRequest(0, Constants.STANDARD_PAGE_SIZE,
							Direction.DESC, "playerId"));
			team.setPlayers(players);
		}
		user.setTeams(teams);
		return user;
	}

	public void save(User user) {
		user.setUserEnabled(true);
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		Collection<Role> roles = new ArrayList<Role>();
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
