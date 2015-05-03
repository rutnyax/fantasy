package com.kossyuzokwe.fantasy.test;

import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;

public class TeamFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Team newTeam() {
		String id = mockValues.nextId();
		Team team = new Team();
		team.setTeamId(id);
		return team;
	}

	public Team newTeam(String id) {
		Team team = new Team();
		team.setTeamId(id);
		return team;
	}

	public Team newTeam(User user) {
		String id = mockValues.nextId();
		Team team = new Team();
		team.setTeamId(id);
		team.setUser(user);
		return team;
	}
	
}
