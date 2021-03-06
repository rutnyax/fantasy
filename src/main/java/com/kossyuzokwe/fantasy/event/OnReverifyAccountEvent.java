package com.kossyuzokwe.fantasy.event;

import com.kossyuzokwe.fantasy.model.User;

public class OnReverifyAccountEvent extends OnUserEvent {
	
	private static final long serialVersionUID = 8229523073886807438L;

	public OnReverifyAccountEvent(User user, String appUrl) {
		super(user, appUrl);
	}

	public User getUser() {
		return user;
	}

	public String getAppUrl() {
		return appUrl;
	}

}
