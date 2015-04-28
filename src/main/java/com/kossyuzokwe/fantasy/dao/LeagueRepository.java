package com.kossyuzokwe.fantasy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.User;

public interface LeagueRepository extends JpaRepository<League, String> {

	Collection<League> findLeaguesByOwner(User user);
}
