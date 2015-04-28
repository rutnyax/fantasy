package com.kossyuzokwe.fantasy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

import com.kossyuzokwe.fantasy.validation.UniqueUsername;

@Entity
@Table(name = "`user`")
public class User {

	@Id
	@GenericGenerator(name = "sequence_object_id", strategy = "com.kossyuzokwe.fantasy.util.ObjectIdGenerator")
	@GeneratedValue(generator = "sequence_object_id")
	@Column(name = "user_id")
	private String userId;

	@Size(min = 3, message = "Name must be at least 3 characters.")
	@Column(name = "user_name", unique = true)
	@UniqueUsername(message="Username already exists.")
	private String userName;

	@Email(message = "Invalid email address.")
	@Size(min = 1, message = "Invalid email address.")
	@Column(name = "user_email")
	private String userEmail;

	@Size(min = 3, message = "Password must be at least 3 characters.")
	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_enabled")
	private boolean userEnabled;

	@ManyToMany
	@JoinTable
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Team> teams;

	@OneToMany(mappedBy = "owner")
	private List<League> leaguesOwned;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<League> getLeaguesOwned() {
		return leaguesOwned;
	}

	public void setLeaguesOwned(List<League> leaguesOwned) {
		this.leaguesOwned = leaguesOwned;
	}

	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}
}
