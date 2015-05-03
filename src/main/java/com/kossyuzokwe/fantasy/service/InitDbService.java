package com.kossyuzokwe.fantasy.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
import com.kossyuzokwe.fantasy.util.Constants;

@Service
public class InitDbService implements
		ApplicationListener<ContextRefreshedEvent> {

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
		Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
		Role userRole = createRoleIfNotFound("ROLE_USER");
		User admin = createUserIfNotFound("admin", "admin", "admin@admin.com", Arrays.asList(adminRole, userRole));
		User test = createUserIfNotFound("test", "test", "test@test.com", Arrays.asList(userRole));
		User none = createUserIfNotFound("none", "none", "none@none.com", Arrays.asList(userRole));
		User someone = createUserIfNotFound("someone", "someone", "someone@someone.com", Arrays.asList(userRole));
		User other = createUserIfNotFound("other", "other", "other@other.com", Arrays.asList(userRole));
		League barclays = createLeagueIfNotFound("Barclays Premier League", admin);
		League bbva = createLeagueIfNotFound("Liga BBVA", someone);
		barclays = joinLeagueIfNotAMember(barclays, test);
		barclays = joinLeagueIfNotAMember(barclays, none);
		bbva = joinLeagueIfNotAMember(bbva, other);
		bbva = joinLeagueIfNotAMember(bbva, none);
		List<Player> chelseaPlayers = createPlayersIfNotFound(Constants.CHELSEA_PLAYER_NAMES);
		List<Player> arsenalPlayers = createPlayersIfNotFound(Constants.ARSENAL_PLAYER_NAMES);
		List<Player> madridPlayers = createPlayersIfNotFound(Constants.MADRID_PLAYER_NAMES);
		List<Player> barcelonaPlayers = createPlayersIfNotFound(Constants.BARCELONA_PLAYER_NAMES);
		Team chelsea = createTeamIfNotFound("Chelsea FC", admin, barclays);
		Team arsenal = createTeamIfNotFound("Arsenal", test, barclays);
		Team madrid = createTeamIfNotFound("Real Madrid C.F.", someone, bbva);
		Team barcelona = createTeamIfNotFound("FC Barcelona", other, bbva);
		chelsea = addPlayersToTeam(chelsea, chelseaPlayers);
		arsenal = addPlayersToTeam(arsenal, arsenalPlayers);
		madrid = addPlayersToTeam(madrid, madridPlayers);
		barcelona = addPlayersToTeam(barcelona, barcelonaPlayers);
	}

	@Transactional
	private Team addPlayersToTeam(Team team, List<Player> players) {
		Team teamFound = teamRepository.findOne(team.getTeamId());
		if (teamFound == null) {
			teamFound = new Team(team.getTeamName(), team.getUser(), team.getLeague());
		}
		teamFound.setPlayers(players);
		return teamFound;
	}

	@Transactional
	private Team createTeamIfNotFound(String teamName, User user, League league) {
		Team team = teamRepository.findByLeagueAndUser(league, user);
		if (team == null) {
			team = new Team(teamName, user, league);
			team = teamRepository.save(team);
		}
		return team;
	}

	@Transactional
	private User createUserIfNotFound(String username, String password, String email, List<Role> roles) {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			user = new User();
			user.setUserName(username);
			user.setUserPassword(passwordEncoder.encode(password));
			user.setUserEmail(email);
			user.setRoles(roles);
			user.setUserEnabled(true);
			user = userRepository.save(user);
		}
		return user;
	}

	@Transactional
	private League createLeagueIfNotFound(String leagueName, User owner) {
		League league = leagueRepository.findByLeagueName(leagueName);
		if (league == null) {
			league = new League(leagueName, owner);
			league = leagueRepository.save(league);
		}
		return league;
	}

	@Transactional
	private League joinLeagueIfNotAMember(League league, User user) {
		League modifiedLeague = leagueRepository.findByIdFetchMembers(league.getLeagueId());
		List<User> currentMembers = modifiedLeague.getMembers();
		if (!currentMembers.contains(user)) {
			currentMembers.add(user);
			modifiedLeague.setMembers(currentMembers);
			modifiedLeague = leagueRepository.save(modifiedLeague);
		}
		return modifiedLeague;
	}

	@Transactional
	private List<Player> createPlayersIfNotFound(Collection<String> playerNames) {
		List<Player> playersFound = playerNames.stream().map(playerRepository::findByPlayerName).collect(Collectors.toList());
		List<String> notFound = playerNames.stream().filter(new Predicate<String>() {

			@Override
			public boolean test(String name) {
				return playerRepository.findByPlayerName(name) == null;
			}
			
		}).collect(Collectors.toList());
		List<Player> newPlayers = notFound.stream().map(name -> new Player(name)).collect(Collectors.toList());
		newPlayers = playerRepository.save(newPlayers);
		if (!newPlayers.isEmpty()) {
			playersFound.addAll(newPlayers);
		}
		return playersFound;
	}

	@Transactional
	private Role createRoleIfNotFound(String name) {
		Role role = roleRepository.findByRoleName(name);
		if (role == null) {
			role = new Role(name);
			role = roleRepository.save(role);
		}
		return role;
	}
}
