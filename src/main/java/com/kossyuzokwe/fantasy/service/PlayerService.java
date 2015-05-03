package com.kossyuzokwe.fantasy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kossyuzokwe.fantasy.dao.PlayerRepository;
import com.kossyuzokwe.fantasy.model.Player;

@Service
public class PlayerService {

	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private PlayerRepository playerRepository;

	@Transactional
	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}
}
