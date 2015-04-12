package com.kossyuzokwe.fantasy.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.entity.League;
import com.kossyuzokwe.fantasy.entity.Player;
import com.kossyuzokwe.fantasy.entity.Role;
import com.kossyuzokwe.fantasy.entity.Team;
import com.kossyuzokwe.fantasy.entity.User;
import com.kossyuzokwe.fantasy.repository.FixtureRepository;
import com.kossyuzokwe.fantasy.repository.LeagueRepository;
import com.kossyuzokwe.fantasy.repository.PlayerRepository;
import com.kossyuzokwe.fantasy.repository.RoleRepository;
import com.kossyuzokwe.fantasy.repository.TeamRepository;
import com.kossyuzokwe.fantasy.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private LeagueRepository leagueRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private FixtureRepository fixtureRepository;

	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setRoleName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setRoleName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setUserName("admin");
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleUser);
		roles.add(roleAdmin);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);

		League premierLeague = new League();
		premierLeague.setLeagueName("Barclays Premier League");
		premierLeague.setOwner(userAdmin);
		leagueRepository.save(premierLeague);

		Team teamChelsea = new Team();
		teamChelsea.setTeamName("Chelsea FC");
		teamChelsea.setUser(userAdmin);
		teamChelsea.setLeague(premierLeague);
		teamRepository.save(teamChelsea);

		Player playerHazard = new Player();
		playerHazard.setPlayerName("Eden Hazard");
		playerHazard.setTeam(teamChelsea);
		playerRepository.save(playerHazard);

		Player playerTerry = new Player();
		playerTerry.setPlayerName("John Terry");
		playerTerry.setTeam(teamChelsea);
		playerRepository.save(playerTerry);
	}
}
