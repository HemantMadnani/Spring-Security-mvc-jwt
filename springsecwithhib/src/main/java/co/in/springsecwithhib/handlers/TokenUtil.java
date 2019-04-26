package co.in.springsecwithhib.handlers;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import co.in.springsecwithhib.model.UserSecurity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1164899878821623087L;

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";

	// private static final String AUDIENCE_UNKNOWN = "unknown";
	// private static final String AUDIENCE_WEB = "web";
	// private static final String AUDIENCE_MOBILE = "mobile";
	// private static final String AUDIENCE_TABLET = "tablet";

	private final String secret = "apexx-secret-key";

	private final Long expiration = (long) 1000000;

	public String getUsernameFromToken(final String token) {

		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (final Exception e) {
			username = null;
		}
		return username;
	}

	public Date getCreatedDateFromToken(final String token) {

		Date created;
		try {
			final Claims claims = getClaimsFromToken(token);
			created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
		} catch (final Exception e) {
			created = null;
		}
		return created;
	}

	public Date getExpirationDateFromToken(final String token) {

		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (final Exception e) {
			expiration = null;
		}
		return expiration;
	}

	/*
	 * public String getAudienceFromToken(final String token) {
	 *
	 * String audience; try { final Claims claims = getClaimsFromToken(token); audience = (String) claims.get(CLAIM_KEY_AUDIENCE); } catch (final Exception e) {
	 * audience = null; } return audience; }
	 */

	private Claims getClaimsFromToken(final String token) {

		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (final Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateExpirationDate() {

		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	private Boolean isTokenExpired(final String token) {

		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean isCreatedBeforeLastPasswordReset(final Date created, final Date lastPasswordReset) {

		return lastPasswordReset != null && created.before(lastPasswordReset);
	}

	/*
	 * private String generateAudience(final Device device) { String audience = AUDIENCE_UNKNOWN; if (device.isNormal()) { audience = AUDIENCE_WEB; } else if
	 * (device.isTablet()) { audience = AUDIENCE_TABLET; } else if (device.isMobile()) { audience = AUDIENCE_MOBILE; } return audience; }
	 */

	/*
	 * private Boolean ignoreTokenExpiration(final String token) {
	 *
	 * final String audience = getAudienceFromToken(token); return AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience); }
	 */

	public String generateToken(final UserSecurity userDetails) {

		final Map<String, Object> claims = new HashMap<>();
		// claims.put(CLAIM_KEY_USERNAME, userDetails.getUser());
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		// claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	String generateToken(final Map<String, Object> claims) {

		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/*
	 * public Boolean canTokenBeRefreshed(final String token, final Date lastPasswordReset) {
	 *
	 * final Date created = getCreatedDateFromToken(token); return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && (!isTokenExpired(token) ||
	 * ignoreTokenExpiration(token)); }
	 */

	public String refreshToken(final String token) {

		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			// claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = generateToken(claims);
		} catch (final Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	public Boolean validateToken(final String token, final UserDetails userDetails) {

		final UserSecurity user = (UserSecurity) userDetails;
		final String username = getUsernameFromToken(token);
		// final Date created = getCreatedDateFromToken(token);
		// final Date expiration = getExpirationDateFromToken(token);
		return username.equals(user.getUsername()) && !isTokenExpired(token);
	}
}
