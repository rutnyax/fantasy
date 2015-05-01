package com.kossyuzokwe.fantasy.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "`team`")
public class Team {

	@Id
	@GenericGenerator(name = "sequence_object_id", strategy = "com.kossyuzokwe.fantasy.util.ObjectIdGenerator")
	@GeneratedValue(generator = "sequence_object_id")
	@Column(name = "team_id")
	private String teamId;

	@Size(min = 1, message = "Name must be at least 1 character.")
	@Column(name = "team_name")
	private String teamName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@ManyToMany
	@JoinTable
	private List<Player> players;

	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;
	
	public Team() {
		super();
		this.createdAt = new Timestamp(new Date().getTime());
	}
	
	public Team(String teamName) {
		super();
		this.teamName = teamName;
		this.createdAt = new Timestamp(new Date().getTime());
	}
	
	public Team(String teamName, User user) {
		super();
		this.teamName = teamName;
		this.user = user;
		this.createdAt = new Timestamp(new Date().getTime());
	}
	
	public Team(String teamName, User user, League league) {
		super();
		this.teamName = teamName;
		this.user = user;
		this.league = league;
		this.createdAt = new Timestamp(new Date().getTime());
	}

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

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
