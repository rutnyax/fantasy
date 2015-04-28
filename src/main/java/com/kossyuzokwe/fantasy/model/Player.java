package com.kossyuzokwe.fantasy.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@ManyToMany(mappedBy = "players")
	private Collection<League> leagues;

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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Collection<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(Collection<League> leagues) {
		this.leagues = leagues;
	}
}
