package com.kossyuzokwe.fantasy.event;

import com.kossyuzokwe.fantasy.model.User;

public class OnRegistrationCompleteEvent extends OnUserEvent {
	
	private static final long serialVersionUID = 2308455175830712271L;

	public OnRegistrationCompleteEvent(User user, String appUrl) {
		super(user, appUrl);
	}

	public User getUser() {
		return user;
	}

	public String getAppUrl() {
		return appUrl;
	}

}
