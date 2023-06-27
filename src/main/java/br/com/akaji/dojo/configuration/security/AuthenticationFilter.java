package br.com.akaji.dojo.configuration.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.akaji.dojo.repositories.UserLoginRepository;
import br.com.akaji.dojo.security.UserLogin;

public class AuthenticationFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserLoginRepository userLoginRepository;

	public AuthenticationFilter(TokenService tokenService, UserLoginRepository userLoginRepository) {
		this.tokenService = tokenService;
		this.userLoginRepository = userLoginRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		boolean validateToken = tokenService.ValidateToken(token);
		if (validateToken) {
			authenticateUser(token);
		}
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			token = null;
		} else
			token = token.substring(7, token.length());
		return token;
	}

	private void authenticateUser(String token) {
		UserLogin user = new UserLogin();
		Object userId = tokenService.getUserId(token);
		Optional<UserLogin> optionalUserLogin = userLoginRepository.findById(userId.toString());

		if (optionalUserLogin.isPresent()) {
			user = optionalUserLogin.get();
		}

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

}
