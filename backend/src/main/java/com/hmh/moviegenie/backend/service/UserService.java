package com.hmh.moviegenie.backend.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hmh.moviegenie.backend.domain.Users;
import com.hmh.moviegenie.backend.dto.UserDetail;
import com.hmh.moviegenie.backend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("유저Id " + userId); 
		Optional<Users> userData = userRepository.findById(userId);
		
		
		 // 사용자가 없으면 예외 던지기
        if (userData.isEmpty()) {
            throw new UsernameNotFoundException(" userId: " + userId);
        }

        // 유효한 UserDetails 반환
        return new UserDetail(userData.get());
	}

	public Users getUser(String userId) {
		Optional<Users> user=userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}

	
}
