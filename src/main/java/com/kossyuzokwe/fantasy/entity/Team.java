package com.kossyuzokwe.fantasy.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "`team`")
public class Team {

	@Id
	@GenericGenerator(name = "sequence_object_id", strategy = "com.kossyuzokwe.fantasy.entity.ObjectIdGenerator")
	@GeneratedValue(generator = "sequence_object_id")
	@Column(name = "team_id")
	private String teamId;

	@Column(name = "team_name")
	private String teamName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "team")
	private List<Player> players;

	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
}
