package com.kossyuzokwe.fantasy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.service.TeamService;
import com.kossyuzokwe.fantasy.service.UserService;

@Controller
public class TeamController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	@RequestMapping("/team/remove/{id}")
	public String removeTeam(@PathVariable String id) {
		Team team = teamService.findOne(id);
		teamService.delete(team);
		return "redirect:/account.html";
	}
	
/*
 * --------------------------------------------------------------------------------------
 * ------------------------------------- NEW REST METHODS -------------------------------
 * --------------------------------------------------------------------------------------
	
	@RequestMapping(method = RequestMethod.GET)
	public String listTeams(Model model) {
		model.addAttribute("teams", teamService.findAll());
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createTeam(Model model,@Valid @ModelAttribute("team") Team team, Principal principal,BindingResult result) {
		if (result.hasErrors()) {
			String name = principal.getName();
			model.addAttribute("user", userService.findOneWithTeamsByName(name));
			return null;
		}
		String name = principal.getName();
		teamService.save(team, name);
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getTeam(Model model,@PathVariable String id) {
		model.addAttribute("team", teamService.findOne(id));
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String updateTeam(Model model,@Valid @ModelAttribute("team") Team team,@PathVariable String id,Principal principal,BindingResult result) {
		if (result.hasErrors()) {
			String name = principal.getName();
			model.addAttribute("user", userService.findOneWithTeamsByName(name));
			return null;
		}
		String name = principal.getName();
		teamService.save(team, name);
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String deleteTeam(@PathVariable String id) {
		Team team = teamService.findOne(id);
		teamService.delete(team);
		return null;
	}
	*/
}
