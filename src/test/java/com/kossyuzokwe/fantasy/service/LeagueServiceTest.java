package com.kossyuzokwe.fantasy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.kossyuzokwe.fantasy.dao.LeagueRepository;
import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.test.LeagueFactoryForTest;
import com.kossyuzokwe.fantasy.test.MockValues;
import com.kossyuzokwe.fantasy.test.TeamFactoryForTest;
import com.kossyuzokwe.fantasy.test.UserFactoryForTest;

@RunWith(MockitoJUnitRunner.class)
public class LeagueServiceTest {

	@InjectMocks
	private LeagueService leagueService;
	@Mock
	private LeagueRepository leagueRepository;
	
	private LeagueFactoryForTest leagueFactoryForTest = new LeagueFactoryForTest();
	
	private UserFactoryForTest userFactoryForTest = new UserFactoryForTest();
	
	private TeamFactoryForTest teamFactoryForTest = new TeamFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void getById() {
		// Given
		String id = mockValues.nextId();
		League league = leagueFactoryForTest.newLeague(id);
		when(leagueRepository.findByLeagueId(id)).thenReturn(league);

		// When
		League leagueFound = leagueService.getById(id);

		// Then
		assertEquals(league.getLeagueId(),leagueFound.getLeagueId());
	}


	@Test
	public void getAll() {
		// Given
		List<League> leagues = new ArrayList<League>();
		League league1 = leagueFactoryForTest.newLeague();
		leagues.add(league1);
		League league2 = leagueFactoryForTest.newLeague();
		leagues.add(league2);
		when(leagueRepository.findAll()).thenReturn(leagues);
		
		// When
		List<League> leaguesFound = leagueService.getAll();

		// Then
		assertTrue(league1 == leaguesFound.get(0));
		assertTrue(league2 == leaguesFound.get(1));
	}
	
	@Test
	public void isOwner() {
		// Given
		User owner = userFactoryForTest.newUser();
		User user = userFactoryForTest.newUser();
		League league = leagueFactoryForTest.newLeagueWithOwner(owner);
//		when(league.getOwner()).thenReturn(owner);
		
		// When
		boolean isOwner = leagueService.isOwner(owner, league);
		boolean isNotOwner = leagueService.isOwner(user, league);

		// Then
		assertTrue(isOwner);
		assertFalse(isNotOwner);
	}
	
	@Test
	public void isMember() {
		// Given
		User member = userFactoryForTest.newUser();
		User nonMember = userFactoryForTest.newUser();
		League league = leagueFactoryForTest.newLeagueWithMembers(Arrays.asList(member));
		when(leagueRepository.findByIdFetchMembers(league.getLeagueId())).thenReturn(league);
		
		// When
		boolean isMember = leagueService.isMember(member, league);
		boolean isNotMember = leagueService.isMember(nonMember, league);
		
		// Then
		assertTrue(isMember);
		assertFalse(isNotMember);
	}
	
	@Test
	public void hasTeam() {
		// Given
		User teamUser = userFactoryForTest.newUser();
		Team team = teamFactoryForTest.newTeam(teamUser);
		User noTeamUser = userFactoryForTest.newUser();
		League league = leagueFactoryForTest.newLeagueWithTeams(Arrays.asList(team));
		when(leagueRepository.findByIdFetchTeams(league.getLeagueId())).thenReturn(league);
		
		// When
		boolean hasTeam = leagueService.hasTeam(teamUser, league);
		boolean hasNoTeam = leagueService.hasTeam(noTeamUser, league);
		
		// Then
		assertTrue(hasTeam);
		assertFalse(hasNoTeam);
	}

	@Test
	public void delete() {
		// Given
		String id = mockValues.nextId();

		// When
		leagueService.delete(id);

		// Then
		verify(leagueRepository).delete(id);
	}

}
