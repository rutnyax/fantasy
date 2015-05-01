package com.kossyuzokwe.fantasy.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "`player`")
public class Player {

	@Id
	@GenericGenerator(name = "sequence_object_id", strategy = "com.kossyuzokwe.fantasy.util.ObjectIdGenerator")
	@GeneratedValue(generator = "sequence_object_id")
	@Column(name = "player_id")
	private String playerId;

	@Column(name = "player_name")
	private String playerName;

	@ManyToMany(mappedBy = "players")
	private List<League> leagues;

	@ManyToMany(mappedBy="players")
	private List<Team> teams;

	@Column(name = "created_at")
	private Timestamp createdAt;

	public Player() {
		super();
		this.leagues = new ArrayList<League>();
		this.teams = new ArrayList<Team>();
		this.createdAt = new Timestamp(new Date().getTime());
	}

	public Player(String playerName) {
		super();
		this.playerName = playerName;
		this.leagues = new ArrayList<League>();
		this.teams = new ArrayList<Team>();
		this.createdAt = new Timestamp(new Date().getTime());
	}
	
	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}
