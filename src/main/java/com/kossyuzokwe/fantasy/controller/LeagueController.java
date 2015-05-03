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

import com.kossyuzokwe.fantasy.model.League;
import com.kossyuzokwe.fantasy.model.Team;
import com.kossyuzokwe.fantasy.model.User;
import com.kossyuzokwe.fantasy.service.LeagueService;
import com.kossyuzokwe.fantasy.service.TeamService;
import com.kossyuzokwe.fantasy.service.UserService;

@Controller
@RequestMapping("/leagues")
public class LeagueController {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private TeamService teamService;

	@Autowired
	private LeagueService leagueService;

	@ModelAttribute("league")
	public League constructLeague() {
		return new League();
	}

	@ModelAttribute("team")
	public Team constructTeam() {
		return new Team();
	}
	
	@ModelAttribute("joinLeague")
	public JoinLeague constructJoinLeague() {
		return new JoinLeague();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listLeagues(Model model, Principal principal) {
		User user = userService.findUserByUserName(principal.getName());
		List<League> ownedLeagues = leagueService.getOwnedLeagues(user);
		List<League> memberLeagues = leagueService.getMemberLeagues(user);
		List<League> otherLeagues = leagueService.getNonMemberLeagues(user);
		model.addAttribute("owned", leagueService.fetchTeams(ownedLeagues));
		model.addAttribute("member", leagueService.fetchTeams(memberLeagues));
		model.addAttribute("other", leagueService.fetchTeams(otherLeagues));
		return "leagues/list";
	}

	@RequestMapping("/create")
	public String showCreateLeague() {
		return "leagues/create";
	}

	@RequestMapping("/available")
	@ResponseBody
	public String availableName(@RequestParam String name) {
		Boolean available = leagueService.getByName(name) == null;
		return available.toString();
	}

	@RequestMapping(value="/create",method = RequestMethod.POST)
	public String createLeague(@Valid @ModelAttribute("league") League league,
			Principal principal, BindingResult result) {
		if (result.hasErrors()) {
			return "leagues/create";
		}
		User user = userService.findUserByUserName(principal.getName());
		leagueService.createLeague(league, user);
		return "redirect:/leagues.html";
	}

	@RequestMapping("/{id}")
	public String getLeague(Model model, @PathVariable String id, 
			Principal principal) {
		User user = userService.findUserByUserName(principal.getName());
		League league = leagueService.getById(id);
		model.addAttribute("isOwner", leagueService.isOwner(user, league));
		model.addAttribute("isMember", leagueService.isMember(user, league));
		model.addAttribute("hasTeam", leagueService.hasTeam(user, league));
		model.addAttribute("league", leagueService.fetchTeam(league));
		return "leagues/view";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String createTeam(Model model, @PathVariable String id,
			@ModelAttribute("team") Team team, Principal principal) {
		String username = principal.getName();
		teamService.createTeam(team, username, id);
		return String.format("redirect:/leagues/%s.html", id);
	}

	@RequestMapping("/join/{id}")
	@ResponseBody
	public String validCode(@PathVariable String id, @RequestParam String passcode) {
		Boolean valid = leagueService.isValidCode(id, passcode);
		return valid.toString();
	}

	@RequestMapping(value = "/join/{id}", method = RequestMethod.POST)
	public String joinLeague(@PathVariable String id, Principal principal,
			@ModelAttribute("joinLeague") JoinLeague joinLeague) {
		if (leagueService.isValidCode(id, joinLeague.getPasscode())) {
			User user = userService.findUserByUserName(principal.getName());
			League existingLeague = leagueService.getById(id);
			leagueService.joinLeague(existingLeague, user);
		}
		return String.format("redirect:/leagues/%s.html", id);
	}

	@RequestMapping("/edit/{id}")
	public String showEditLeague(Model model, @PathVariable String id) {
		model.addAttribute("league", leagueService.getById(id));
		return "leagues/edit";
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String editLeague(@Valid @ModelAttribute("league") League league,
			@PathVariable String id, RedirectAttributes redirectAttributes,
			BindingResult result) {
		if (result.hasErrors()) {
			return "leagues/edit";
		}
		redirectAttributes.addFlashAttribute("league", leagueService.updateLeague(id, league));
		redirectAttributes.addFlashAttribute("success", true);
		return String.format("redirect:/leagues/edit/%s.html", id);
	}

	@RequestMapping("/remove/{id}")
	public String deleteLeague(@PathVariable String id) {
		leagueService.delete(id);
		return "redirect:/leagues.html";
	}

	@SuppressWarnings("unused")
	private class JoinLeague {
		private String passcode;
		public String getPasscode() {
			return passcode;
		}
		public void setPasscode(String passcode) {
			this.passcode = passcode;
		}
	}
}
