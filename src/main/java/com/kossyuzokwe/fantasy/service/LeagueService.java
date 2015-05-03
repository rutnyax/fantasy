package com.kossyuzokwe.fantasy.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.dao.LeagueRepository;
import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;

@Service
public class LeagueService {

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private LeagueRepository leagueRepository;

	@Transactional
	public List<League> getOwnedLeagues(User user) {
		return leagueRepository.findByOwnerFetchMembers(user.getUserId());
	}

	@Transactional
	public List<League> getMemberLeagues(User user) {
		List<League> allLeagues = leagueRepository.findAllFetchMembers();
		return allLeagues
				.stream()
				.filter(league -> league
						.getMembers()
						.stream()
						.anyMatch(
								member -> (user.getUserId().equals(member
										.getUserId()))))
				.collect(Collectors.toList());
	}

	@Transactional
	public List<League> fetchTeams(List<League> leagues) {
		for (League league : leagues) {
			League teamLeague = leagueRepository.findByIdFetchTeams(league.getLeagueId());
			List<Team> teams = teamLeague.getTeams();
			league.setTeams(teams);
		}
		return leagues;
	}

	@Transactional
	public League fetchTeam(League league) {
		League teamLeague = leagueRepository.findByIdFetchTeams(league.getLeagueId());
		List<Team> teams = teamLeague.getTeams();
		league.setTeams(teams);
		return league;
	}

	@Transactional
	public List<League> getNonMemberLeagues(User user) {
		List<League> allLeagues = leagueRepository.findAllFetchMembers();
		return allLeagues
				.stream()
				.filter(league -> league
						.getMembers()
						.stream()
						.allMatch(
								member -> !user.getUserId().equals(
										member.getUserId())
										&& !user.getUserId().equals(
												league.getOwner().getUserId())))
				.collect(Collectors.toList());
	}

	@Transactional
	public void delete(String id) {
		leagueRepository.delete(id);
	}

	@Transactional
	public League getById(String id) {
		return leagueRepository.findByLeagueId(id);
	}
	
	@Transactional
	public boolean isOwner(User user, League league) {
		return league.getOwner().getUserId().equals(user.getUserId());
	}
	
	@Transactional
	public boolean hasTeam(User user, League league) {
		League retrievedLeague = leagueRepository.findByIdFetchTeams(league
				.getLeagueId());
		return retrievedLeague.getTeams().stream()
				.map(team -> team.getUser().getUserId())
				.anyMatch(id -> id.equals(user.getUserId()));
	}
	
	@Transactional
	public boolean isMember(User user, League league) {
		League retrievedLeague = leagueRepository.findByIdFetchMembers(league
				.getLeagueId());
		return retrievedLeague.getMembers().stream()
				.map(member -> member.getUserId())
				.anyMatch(id -> id.equals(user.getUserId()));
	}

	@Transactional
	public void joinLeague(League league, User user) {
		League modifiedLeague = leagueRepository.findByIdFetchMembers(league.getLeagueId());
		List<User> currentMembers = modifiedLeague.getMembers();
		if (!currentMembers.contains(user)) {
			currentMembers.add(user);
			modifiedLeague.setMembers(currentMembers);
			leagueRepository.save(modifiedLeague);
		}
	}

	@Transactional
	public List<League> getAll() {
		return leagueRepository.findAll();
	}

	@Transactional
	public boolean isValidCode(String leagueId, String passcode) {
		return leagueRepository.findByLeagueId(leagueId).getPasscode().equals(passcode);
	}

	@Transactional
	public League updateLeague(String id, League league) {
		League existingLeague = leagueRepository.findByLeagueId(id);
		existingLeague.setLeagueName(league.getLeagueName());
		return leagueRepository.save(existingLeague);
	}

	@Transactional
	public League createLeague(League league, User user) {
		league.setOwner(user);
		return leagueRepository.save(league);
	}

	@Transactional
	public League getByName(String name) {
		return leagueRepository.findByLeagueName(name);
	}

}
