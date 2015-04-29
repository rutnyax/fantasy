package com.kossyuzokwe.fantasy.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
public class InitDbService implements ApplicationListener<ContextRefreshedEvent> {

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
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");
		createAdminIfNotFound();
		createTestUserIfNotFound();
	}

	@Transactional
	private Role createRoleIfNotFound(String name) {
		Role role = roleRepository.findByRoleName(name);
		if (role == null) {
			role = new Role(name);
			roleRepository.save(role);
		}
		return role;
	}

	@Transactional
	private User createAdminIfNotFound() {
		User user = userRepository.findByUserName("admin");
		if (user == null) {
			Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
			Role userRole = roleRepository.findByRoleName("ROLE_USER");
			User adminUser = new User();
			adminUser.setUserName("admin");
			adminUser.setUserPassword(passwordEncoder.encode("admin"));
			adminUser.setUserEmail("admin@admin.com");
			adminUser.setRoles(Arrays.asList(adminRole, userRole));
			adminUser.setUserEnabled(true);
			user = userRepository.save(adminUser);

			League premierLeague = leagueRepository
					.findByLeagueName("Barclays Premier League");
			if (premierLeague == null) {
				premierLeague = new League();
				premierLeague.setLeagueName("Barclays Premier League");
				premierLeague.setOwner(adminUser);
				leagueRepository.save(premierLeague);
			}
			Team teamChelsea = new Team();
			teamChelsea.setTeamName("Chelsea FC");
			teamChelsea.setUser(adminUser);
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
		return user;
	}

	@Transactional
	private User createTestUserIfNotFound() {
		User user = userRepository.findByUserName("test");
		if (user == null) {
			Role userRole = roleRepository.findByRoleName("ROLE_USER");
			User testUser = new User();
			testUser.setUserName("test");
			testUser.setUserPassword(passwordEncoder.encode("test"));
			testUser.setUserEmail("test@test.com");
			testUser.setRoles(Arrays.asList(userRole));
			testUser.setUserEnabled(true);
			user = userRepository.save(testUser);

			League premierLeague = leagueRepository
					.findByLeagueName("Barclays Premier League");
			if (premierLeague != null) {
				Team teamArsenal = new Team();
				teamArsenal.setTeamName("Arsenal");
				teamArsenal.setUser(testUser);
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
		return user;
	}
}
