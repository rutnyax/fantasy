package com.kossyuzokwe.fantasy.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

import com.kossyuzokwe.fantasy.validation.UniqueEmail;
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
	@UniqueUsername(message = "Username already exists.")
	private String userName;

	@Email(message = "Invalid email address.")
	@Size(min = 1, message = "Invalid email address.")
	@Column(name = "user_email", unique = true)
	@UniqueEmail(message = "Email address already exists.")
	private String userEmail;

	@Size(min = 5, message = "Password must be at least 5 characters.")
	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_enabled")
	private boolean userEnabled;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@ManyToMany
	@JoinTable
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Team> teams;

	@OneToMany(mappedBy = "owner")
	private List<League> ownerships;

	@ManyToMany(mappedBy = "members")
	private List<League> leagues;

	public User() {
		super();
		this.leagues = new ArrayList<League>();
		this.ownerships = new ArrayList<League>();
		this.teams = new ArrayList<Team>();
		this.createdAt = new Timestamp(new Date().getTime());
		this.userEnabled = false;
	}

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

	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User user = (User) obj;
		if (!userEmail.equals(user.userEmail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User {name=").append(userName).append(", ")
				.append("email=").append(userEmail).append("}");
		return builder.toString();
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public List<League> getOwnerships() {
		return ownerships;
	}

	public void setOwnerships(List<League> ownerships) {
		this.ownerships = ownerships;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}
}
