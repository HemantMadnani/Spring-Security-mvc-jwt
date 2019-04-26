package co.in.springsecwithhib.handlers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import co.in.springsecwithhib.service.UserSecurityService;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private UserSecurityService userSecurityService;

	private final String tokenHeader = "apexxkey";

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
			throws ServletException, IOException {

		String authToken = request.getHeader(tokenHeader);

		if (authToken != null && authToken.startsWith("Bearer ")) {
			authToken = authToken.replace("Bearer ", "");
		}
		final String username = tokenUtil.getUsernameFromToken(authToken);
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails userDetails = userSecurityService.loadUserByUsername(username);

			if (tokenUtil.validateToken(authToken, userDetails)) {
				final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
