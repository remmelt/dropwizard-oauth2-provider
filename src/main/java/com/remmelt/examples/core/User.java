package com.remmelt.examples.core;

/**
 * User of the application.
 */
public class User {

	private Long id;
	private String username;
	private String password;

	public User(Long id, String name, String pwd) {
		this.username = name;
		this.password = pwd;
		this.id = id;
	}

	/**
	 * Getter for user ID.
	 *
	 * @return user ID.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for user ID
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * Getter for username.
	 *
	 * @return user name.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter for user name.
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter for password.
	 *
	 * @return user password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for user password.
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}