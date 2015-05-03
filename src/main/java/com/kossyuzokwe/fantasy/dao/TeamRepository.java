package com.kossyuzokwe.fantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;

public interface TeamRepository extends JpaRepository<Team, String>{

	Team findByLeagueAndUser(League league, User user);

	Team findByTeamId(String teamId);

	List<Team> findByUser(User user);

	List<Team> findByLeague(League league);
	
	@Query("SELECT t FROM Team t LEFT JOIN FETCH t.players WHERE t.teamId = :id")
	Team findByTeamIdFetchPlayers(@Param("id") String id);
	
	@Query("SELECT DISTINCT t FROM Team t LEFT JOIN FETCH t.players WHERE t.user.userId = :id")
	List<Team> findByUserFetchPlayers(@Param("id") String id);
}
