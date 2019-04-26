package co.in.springsecwithhib.handlers;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8619857337012081353L;

	private String username;
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {

		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {

		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {

		this.password = password;
	}

	public AuthenticationRequest(final String username, final String password) {

		super();
		this.username = username;
		this.password = password;
	}

	public AuthenticationRequest() {

		super();
	}

}
