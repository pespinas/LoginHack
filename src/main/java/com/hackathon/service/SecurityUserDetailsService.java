package com.hackathon.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hackathon.models.User;
import com.hackathon.repositories.UserRepository;
import com.hackathon.security.SecurityUser;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	public SecurityUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = this.userRepository.findByUsername(username);
		if (optUser.isPresent()) {
			return new SecurityUser(optUser.get());
		}
		throw new UsernameNotFoundException("User not found: " + username);
	}

}