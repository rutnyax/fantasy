package com.kossyuzokwe.fantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.User;

public interface LeagueRepository extends JpaRepository<League, String> {

	List<League> findLeaguesByOwner(User user);

	League findByLeagueName(String name);
}
