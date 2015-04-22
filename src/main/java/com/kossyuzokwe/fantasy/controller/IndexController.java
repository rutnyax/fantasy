package com.kossyuzokwe.fantasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kossyuzokwe.fantasy.service.PlayerService;

@Controller
public class IndexController {
	
	@Autowired
	private PlayerService playerService;

	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("players", playerService.getPlayers());
		return "index";
	}
}
