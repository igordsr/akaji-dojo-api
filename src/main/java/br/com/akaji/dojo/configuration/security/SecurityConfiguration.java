package br.com.akaji.dojo.configuration.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import br.com.akaji.dojo.repositories.UserLoginRepository;

@EnableWebSecurity
@Configuration
//@Profile("prod")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserLoginRepository userLoginRepository;
	
	

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Configurações de Autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}

	// Configurações de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> new CorsConfiguration(this.getCorsConfig()))
			.and().csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.antMatchers(HttpMethod.POST, "/users/checkUser").permitAll()
			.anyRequest().authenticated()
				// .antMatchers(HttpMethod.DELETE, "/*").hasRole("ADM")
				// .and().formLogin()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilterBefore(new AuthenticationFilter(tokenService, userLoginRepository),UsernamePasswordAuthenticationFilter.class);
	}

	// Configurações de recursos estaticos(js, css, img)
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
	
	
	private CorsConfiguration getCorsConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		List<String> allowedMethods = new ArrayList<>();
		
		allowedMethods.add(HttpMethod.GET.name());
		allowedMethods.add(HttpMethod.HEAD.name());
		allowedMethods.add(HttpMethod.POST.name());
		allowedMethods.add(HttpMethod.PUT.name());
		allowedMethods.add(HttpMethod.PATCH.name());
		allowedMethods.add(HttpMethod.DELETE.name());
		allowedMethods.add(HttpMethod.OPTIONS.name());
		allowedMethods.add(HttpMethod.TRACE.name());
		
		corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
		corsConfiguration.setAllowedMethods(allowedMethods);
		corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
		corsConfiguration.setMaxAge(1800L);
		return corsConfiguration;
	}
}
