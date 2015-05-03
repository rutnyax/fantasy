package com.kossyuzokwe.fantasy.test;

import java.util.List;

import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;

public class LeagueFactoryForTest {

	private MockValues mockValues = new MockValues();

	public League newLeague(String id) {
		League league = new League();
		league.setLeagueId(id);
		return league;
	}
	
	public League newLeague() {
		String id = mockValues.nextId();
		return newLeague(id);
	}

	public League newLeagueWithOwner(User owner) {
		League league = newLeague();
		league.setOwner(owner);
		return league;
	}

	public League newLeagueWithMembers(List<User> users) {
		League league = newLeague();
		league.setMembers(users);
		return league;
	}
	
	public League newLeagueWithTeams(List<Team> teams) {
		League league = newLeague();
		league.setTeams(teams);
		return league;
	}
	
}
