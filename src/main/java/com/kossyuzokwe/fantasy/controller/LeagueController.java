package com.kossyuzokwe.fantasy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.service.LeagueService;
import com.kossyuzokwe.fantasy.service.UserService;

@Controller
@RequestMapping("/leagues")
public class LeagueController {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private LeagueService leagueService;

	@ModelAttribute("league")
	public League constructLeague() {
		return new League();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listLeagues() {
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createLeague() {
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getLeague() {
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String updateLeague() {
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String deleteLeague(@PathVariable String id) {
		leagueService.delete(id);
		return null;
	}
}
