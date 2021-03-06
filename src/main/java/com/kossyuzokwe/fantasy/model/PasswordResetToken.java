package com.kossyuzokwe.fantasy.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reset_token")
public class PasswordResetToken {

	private static final int EXPIRATION = 60 * 24;

	@Id
	@GenericGenerator(name = "sequence_object_id", strategy = "com.kossyuzokwe.fantasy.util.ObjectIdGenerator")
	@GeneratedValue(generator = "sequence_object_id")
	@Column(name = "password_reset_token_id")
	private String passwordResetTokenId;

	private String token;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;

	private Date expiryDate;

	public PasswordResetToken() {
		super();
	}

	public PasswordResetToken(String token) {
		super();

		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	public PasswordResetToken(String token, User user) {
		super();

		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	public String getPasswordResetTokenId() {
		return passwordResetTokenId;
	}

	public void setPasswordResetTokenId(String passwordResetTokenId) {
		this.passwordResetTokenId = passwordResetTokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public void updateToken(String token) {
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		PasswordResetToken other = (PasswordResetToken) obj;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Token [String=").append(token).append("]")
				.append("[Expires=").append(expiryDate).append("]");
		return builder.toString();
	}
}
