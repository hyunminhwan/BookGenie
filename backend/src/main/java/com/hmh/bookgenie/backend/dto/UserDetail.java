package com.hmh.bookgenie.backend.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hmh.bookgenie.backend.domain.Users;

public class UserDetail implements UserDetails{

	private final Users users;
	
	public UserDetail(Users users) {
		this.users = users;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return users.getUserRole();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		
		
		return users.getUserPassword();
	}

	@Override
	public String getUsername() {
		
		return users.getUserId();
	}

}
