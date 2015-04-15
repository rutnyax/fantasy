package com.kossyuzokwe.fantasy.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "`fixture`")
public class Fixture {

	@Id
	@GenericGenerator(name = "sequence_object_id", strategy = "com.kossyuzokwe.fantasy.util.ObjectIdGenerator")
	@GeneratedValue(generator = "sequence_object_id")
	@Column(name = "fixture_id")
	private String fixtureId;

	@Column(name = "kickoff_time")
	private Date kickoffTime;

	public String getFixtureId() {
		return fixtureId;
	}

	public void setFixtureId(String fixtureId) {
		this.fixtureId = fixtureId;
	}

	public Date getKickoffTime() {
		return kickoffTime;
	}

	public void setKickoffTime(Date kickoffTime) {
		this.kickoffTime = kickoffTime;
	}
}
