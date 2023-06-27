package br.com.akaji.dojo.configuration.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.akaji.dojo.repositories.UserLoginRepository;
import br.com.akaji.dojo.security.UserLogin;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UserLoginRepository userLogin;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserLogin> optionalUserLogin = userLogin.findByUserName(username);
		if(!optionalUserLogin.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return optionalUserLogin.get();
	}

}
