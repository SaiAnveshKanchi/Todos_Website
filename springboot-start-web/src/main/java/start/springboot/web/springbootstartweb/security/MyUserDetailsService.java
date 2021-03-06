package start.springboot.web.springbootstartweb.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import start.springboot.web.springbootstartweb.model.MyUserDetails;
import start.springboot.web.springbootstartweb.model.User;
import start.springboot.web.springbootstartweb.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user =  userRepository.findByUserName(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found: "+username));
		return user.map(MyUserDetails::new).get();
	}
}
