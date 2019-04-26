package co.in.springsecwithhib.handlers;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String username;
	private final Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(final String accessToken, final String username, final Collection<? extends GrantedAuthority> authorities) {

		token = accessToken;
		this.username = username;
		this.authorities = authorities;
	}

	public String getAccessToken() {

		return token;
	}

	public void setAccessToken(final String accessToken) {

		token = accessToken;
	}

	public String getTokenType() {

		return type;
	}

	public void setTokenType(final String tokenType) {

		type = tokenType;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(final String username) {

		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}
}