package com.kossyuzokwe.fantasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kossyuzokwe.fantasy.model.Player;
import com.kossyuzokwe.fantasy.service.PlayerService;

@Controller
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@ModelAttribute("player")
	public Player constructPlayer() {
		return new Player();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listPlayers() {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createPlayer() {
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getPlayer() {
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String updatePlayer() {
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String deletePlayer() {
		return null;
	}
}
