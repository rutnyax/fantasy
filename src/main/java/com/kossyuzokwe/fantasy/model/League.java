package com.kossyuzokwe.fantasy.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.kossyuzokwe.fantasy.validation.UniqueLeagueName;

@Entity
@Table(name = "`league`")
public class League {

	@Id
	@GenericGenerator(name = "sequence_object_id", strategy = "com.kossyuzokwe.fantasy.util.ObjectIdGenerator")
	@GeneratedValue(generator = "sequence_object_id")
	@Column(name = "league_id")
	private String leagueId;

	@Column(name = "league_name", unique=true)
	@UniqueLeagueName(message = "League name already exists.")
	private String leagueName;

	private String passcode;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User owner;

	@ManyToMany
	@JoinTable
	private List<User> members;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@OneToMany(mappedBy = "league", cascade = CascadeType.REMOVE)
	private List<Team> teams;

	@ManyToMany
	@JoinTable
	private List<Player> players;

	public League() {
		super();
		this.members = new ArrayList<User>();
		this.passcode = UUID.randomUUID().toString();
		this.createdAt = new Timestamp(new Date().getTime());
	}

	public League(String leagueName) {
		super();
		this.leagueName = leagueName;
		this.members = new ArrayList<User>();
		this.passcode = UUID.randomUUID().toString();
		this.createdAt = new Timestamp(new Date().getTime());
	}

	public League(String leagueName, User owner) {
		super();
		this.leagueName = leagueName;
		this.owner = owner;
		this.members = new ArrayList<User>();
		this.passcode = UUID.randomUUID().toString();
		this.createdAt = new Timestamp(new Date().getTime());
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("League {id=").append(leagueId).append(", name=")
				.append(leagueName).append(", owner=").append(owner);
		if (members.isEmpty()) {
			sb.append(", members=").append("EMPTY").append("}");
		} else {
			sb.append(", members=").append(Arrays.toString(members.toArray()))
					.append("}");
		}
		return sb.toString();
	}
}
