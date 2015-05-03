package com.kossyuzokwe.fantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.User;

public interface LeagueRepository extends JpaRepository<League, String> {

	List<League> findLeaguesByOwner(User user);

	League findByLeagueName(String name);

	League findByLeagueId(String leagueId);

	@Query("SELECT l FROM League l LEFT JOIN FETCH l.teams WHERE l.leagueName = :name")
	League findByNameFetchTeams(@Param("name") String name);

	@Query("SELECT l FROM League l LEFT JOIN FETCH l.teams WHERE l.leagueId = :id")
	League findByIdFetchTeams(@Param("id") String id);

	@Query("SELECT l FROM League l LEFT JOIN FETCH l.members WHERE l.leagueId = :id")
	League findByIdFetchMembers(@Param("id") String id);
	
	@Query("SELECT DISTINCT l FROM League l LEFT JOIN FETCH l.teams WHERE l.owner.userId = :id")
	List<League> findByOwnerFetchTeams(@Param("id") String id);
	
	@Query("SELECT DISTINCT l FROM League l LEFT JOIN FETCH l.members WHERE l.owner.userId = :id")
	List<League> findByOwnerFetchMembers(@Param("id") String id);

	@Query("SELECT DISTINCT l FROM League l LEFT JOIN FETCH l.members")
	List<League> findAllFetchMembers();

	@Query("SELECT DISTINCT l FROM League l LEFT JOIN FETCH l.teams")
	List<League> findAllFetchTeams();

	@Query("SELECT DISTINCT l FROM League l LEFT JOIN FETCH l.players")
	List<League> findAllFetchPlayers();
}
