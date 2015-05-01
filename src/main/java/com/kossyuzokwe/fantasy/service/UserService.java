package com.kossyuzokwe.fantasy.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.dao.LeagueRepository;
import com.kossyuzokwe.fantasy.dao.PasswordResetTokenRepository;
import com.kossyuzokwe.fantasy.dao.PlayerRepository;
import com.kossyuzokwe.fantasy.dao.RoleRepository;
import com.kossyuzokwe.fantasy.dao.TeamRepository;
import com.kossyuzokwe.fantasy.dao.UserRepository;
import com.kossyuzokwe.fantasy.dao.VerificationTokenRepository;
import com.kossyuzokwe.fantasy.model.PasswordResetToken;
import com.kossyuzokwe.fantasy.model.Player;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.model.VerificationToken;

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
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private PasswordResetTokenRepository resetTokenRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findUserByUserId(String id) {
		return userRepository.findByUserId(id);
	}

	public User findUserByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	public User findUserByUserEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	public User findOneWithTeamsById(String id) {
		User user = findUserByUserId(id);
		List<Team> teams = teamRepository.findByUser(user);
		for (Team team : teams) {
			List<Player> players = playerRepository.findAll(team.getPlayers()
					.stream().map(Player::getPlayerId)
					.collect(Collectors.toList()));
			team.setPlayers(players);
		}
		user.setTeams(teams);
		return user;
	}

	public User findOneWithTeamsByName(String username) {
		User user = userRepository.findByUserName(username);
		return findOneWithTeamsById(user.getUserId());
	}

	public User findUserByVerificationToken(String verificationToken) {
		return verificationTokenRepository.findByToken(verificationToken)
				.getUser();
	}

	public User findUserByPasswordResetToken(String token) {
		return resetTokenRepository.findByToken(token).getUser();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void deleteUser(String id) {
		userRepository.delete(id);
	}

	public User registerNewUserAccount(User userInfo) {
		User user = new User();
		user.setUserName(userInfo.getUserName());
		user.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
		user.setUserEmail(userInfo.getUserEmail());
		user.setRoles(Arrays.asList(roleRepository.findByRoleName("ROLE_USER")));
		return userRepository.save(user);
	}

	public void createVerificationTokenForUser(User user, String token) {
		VerificationToken myToken = new VerificationToken(token, user);
		verificationTokenRepository.save(myToken);
	}

	public VerificationToken getVerificationToken(String VerificationToken) {
		return verificationTokenRepository.findByToken(VerificationToken);
	}

	public VerificationToken regenerateVerificationToken(
			String existingVerificationToken) {
		VerificationToken vToken = verificationTokenRepository
				.findByToken(existingVerificationToken);
		vToken.updateToken(UUID.randomUUID().toString());
		vToken = verificationTokenRepository.save(vToken);
		return vToken;
	}

	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		resetTokenRepository.save(myToken);
	}

	public PasswordResetToken getPasswordResetToken(String token) {
		return resetTokenRepository.findByToken(token);
	}

	public void changeUserPassword(User user, String password) {
		user.setUserPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}

	public boolean validOldPassword(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getUserPassword());
	}

	public boolean emailExists(String email) {
		User user = userRepository.findByUserEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}
}
