package com.remmelt.examples.db;

import com.google.common.base.Optional;
import com.remmelt.examples.core.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Example UserDAO, currently supports only two hard coded users.
 */
public class UserDAO {
	final static Map<Long, User> userTable = new HashMap<>();

	static {
		userTable.put(1l, new User(1l, "alice", "secret"));
		userTable.put(2l, new User(2l, "bob", "letMeIn"));
	}

	public Optional<User> findUserByUsernameAndPassword(final String username, final String password) {
		for (Map.Entry<Long, User> entry : userTable.entrySet()) {
			User user = entry.getValue();
			if (user.getPassword().equals(password) && user.getUsername().equals(username)) {
				return Optional.of(user);
			}
		}
		return Optional.absent();
	}
}
