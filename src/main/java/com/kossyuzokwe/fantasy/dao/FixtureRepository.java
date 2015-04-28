package com.kossyuzokwe.fantasy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kossyuzokwe.fantasy.model.Fixture;

public interface FixtureRepository extends JpaRepository<Fixture, String>{

}
