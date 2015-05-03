package com.kossyuzokwe.fantasy.test;

import com.kossyuzokwe.fantasy.model.User;

public class UserFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public User newUser() {
		String id = mockValues.nextId();
		User user = new User();
		user.setUserId(id);
		return user;
	}

	public User newUser(String id) {
		User user = new User();
		user.setUserId(id);
		return user;
	}
	
}
