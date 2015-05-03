package com.kossyuzokwe.fantasy.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.service.TeamService;
import com.kossyuzokwe.fantasy.service.UserService;

@Controller
@RequestMapping("/teams")
public class TeamController {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	@ModelAttribute("team")
	public Team constructTeam() {
		return new Team();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listTeams(Model model, Principal principal) {
		User user = userService.findUserByUserName(principal.getName());
		List<Team> teams = teamService.getByUser(user);
		model.addAttribute("teams", teams);
		return "teams/list";
	}

	@RequestMapping("/{id}")
	public String getTeam(Model model, @PathVariable String id,
			Principal principal) {
		User user = userService.findUserByUserName(principal.getName());
		Team team = teamService.getByIdWithPlayers(id);
		model.addAttribute("isOwner", teamService.isOwner(user, team));
		model.addAttribute("team", team);
		return "teams/view";
	}

	@RequestMapping("/edit/{id}")
	public String showEditLeague(Model model, @PathVariable String id) {
		model.addAttribute("team", teamService.getById(id));
		return "teams/edit";
	}

	@RequestMapping("/available")
	@ResponseBody
	public String availableName(@RequestParam String teamname, @RequestParam String leaguename) {
		Boolean available = teamService.isAvailable(teamname, leaguename);
		return available.toString();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editTeam(@Valid @ModelAttribute("team") Team team, 
			@PathVariable String id, RedirectAttributes redirectAttributes, 
			BindingResult result) {
		if (result.hasErrors()) {
			return "teams/edit";
		}
		redirectAttributes.addFlashAttribute("team", teamService.updateTeam(id, team));
		redirectAttributes.addFlashAttribute("success", true);
		return String.format("redirect:/teams/edit/%s.html", id);
	}

	@RequestMapping("/remove/{id}")
	public String removeTeam(@PathVariable String id) {
		teamService.delete(id);
		return "redirect:/teams.html";
	}
}
