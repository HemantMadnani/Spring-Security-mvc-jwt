package co.in.springsecwithhib.handlers;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("httpAuthenticationEntryPoint")
public class HttpAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5550870899682142551L;

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException)
			throws IOException, ServletException {

		System.out.println("Auth Entryh");
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
	}

}
