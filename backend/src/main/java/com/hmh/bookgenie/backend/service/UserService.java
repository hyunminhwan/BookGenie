package com.hmh.bookgenie.backend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hmh.bookgenie.backend.domain.Users;
import com.hmh.bookgenie.backend.dto.UserDetail;
import com.hmh.bookgenie.backend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		Users userData = userRepository.findByUserId(userId);
		
		if(userData != null) {
			return new UserDetail(userData);
		}
		return null;
	}

	
}
