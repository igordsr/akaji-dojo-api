package br.com.akaji.dojo.configuration.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.akaji.dojo.security.UserLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${akaji.jwt.expiration}")
	private String expiration;
	
	@Value("${akaji.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authenticate) {
		UserLogin principal = (UserLogin) authenticate.getPrincipal();
		Date startDate = new Date();
		Date endDate = new Date(startDate.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("AkajiDojo")
				.setSubject(principal.getUsername())
				.setIssuedAt(startDate)
				.setExpiration(endDate)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean ValidateToken(String token) {
		boolean valid = false;
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);	
			valid = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return valid;
	}

	public Object getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();	
		return claims.getSubject();
	}

}
