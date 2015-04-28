package com.kossyuzokwe.fantasy.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.dao.FixtureRepository;
import com.kossyuzokwe.fantasy.dao.LeagueRepository;
import com.kossyuzokwe.fantasy.dao.PlayerRepository;
import com.kossyuzokwe.fantasy.dao.RoleRepository;
import com.kossyuzokwe.fantasy.dao.TeamRepository;
import com.kossyuzokwe.fantasy.dao.UserRepository;
import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.Player;
import com.kossyuzokwe.fantasy.model.Role;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;

@Transactional
@Service
public class InitDbService {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

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
		if (roleRepository.findByRoleName("ROLE_ADMIN") == null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			Role roleUser = new Role();
			roleUser.setRoleName("ROLE_USER");
			roleRepository.save(roleUser);

			Role roleAdmin = new Role();
			roleAdmin.setRoleName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			User userAdmin = new User();
			userAdmin.setUserEnabled(true);
			userAdmin.setUserName("admin");
			userAdmin.setUserEmail("admin@admin.com");
			userAdmin.setUserPassword(encoder.encode("admin"));
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

			User userNotAdmin = new User();
			userNotAdmin.setUserEnabled(true);
			userNotAdmin.setUserName("test");
			userNotAdmin.setUserEmail("test@test.com");
			userNotAdmin.setUserPassword(encoder.encode("test"));
			List<Role> roles2 = new ArrayList<Role>();
			roles2.add(roleUser);
			userNotAdmin.setRoles(roles2);
			userRepository.save(userNotAdmin);

			Team teamArsenal = new Team();
			teamArsenal.setTeamName("Arsenal");
			teamArsenal.setUser(userNotAdmin);
			teamArsenal.setLeague(premierLeague);
			teamRepository.save(teamArsenal);

			Player playerSanchez = new Player();
			playerSanchez.setPlayerName("Alexis Sanchez");
			playerSanchez.setTeam(teamArsenal);
			playerRepository.save(playerSanchez);

			Player playerWalcott = new Player();
			playerWalcott.setPlayerName("Theo Walcott");
			playerWalcott.setTeam(teamArsenal);
			playerRepository.save(playerWalcott);
		}
	}
}
