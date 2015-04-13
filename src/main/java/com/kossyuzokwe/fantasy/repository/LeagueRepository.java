package com.kossyuzokwe.fantasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.entity.League;
import com.kossyuzokwe.fantasy.entity.User;

public interface LeagueRepository extends JpaRepository<League, String> {

	List<League> findLeaguesByOwner(User user);
}
