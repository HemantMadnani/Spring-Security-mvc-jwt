package co.in.springsecwithhib.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.in.springsecwithhib.model.UserSecurity;

/**
 *
 * @author hemant.madnani
 *
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	// @Autowired
	// AuthSuccessHandler(final MappingJackson2HttpMessageConverter converter) {
	//
	// objectMapper() = converter.getObjectMapper();
	// }

	@Bean
	public ObjectMapper objectMapper() {

		return new ObjectMapper();
	}

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtil tokenUtil;

	// @Bean
	// public MappingJackson2HttpMessageConverter converter() {
	//
	// final MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	// jackson2HttpMessageConverter.setObjectMapper(mapper);
	// return jackson2HttpMessageConverter;
	// }

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication)
			throws ServletException, IOException {

		response.setStatus(HttpServletResponse.SC_OK);
		final UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
		// final User user = userSecurity.getUser();
		// userSecurity.setUser(user);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final String jwt = tokenUtil.generateToken(userSecurity);
		final PrintWriter printWriter = response.getWriter();
		objectMapper().writeValue(printWriter, new JwtResponse(jwt, userSecurity.getUsername(), userSecurity.getAuthorities()));
		// objectMapper().writeValue(printWriter, jwt);
		printWriter.flush();

	}

}
