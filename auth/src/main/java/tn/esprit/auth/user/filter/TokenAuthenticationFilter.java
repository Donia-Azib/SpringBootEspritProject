package tn.esprit.auth.user.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.client.HttpServerErrorException;

import com.rabbitmq.client.AMQP.Access.Request;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	
	private final static String BEARER = "Bearer";
	
	
	
	
	
	public TokenAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
		// TODO Auto-generated constructor stub
	}



	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		final String authToken = request.getHeader(AUTHORIZATION);
		final String jwtToken = Optional.ofNullable(authToken).map(token -> StringUtils
				.removeStart(token, BEARER))
				.map(String::trim)
				.orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));
	
		final Authentication auth = new UsernamePasswordAuthenticationToken(jwtToken, jwtToken);
		return getAuthenticationManager().authenticate(auth);
	
	
	}
	
	
	
	

}
