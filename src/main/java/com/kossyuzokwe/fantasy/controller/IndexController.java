package com.kossyuzokwe.fantasy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kossyuzokwe.fantasy.service.PlayerService;

@Controller
public class IndexController {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PlayerService playerService;

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("players", playerService.getPlayers());
		return "index";
	}
}
