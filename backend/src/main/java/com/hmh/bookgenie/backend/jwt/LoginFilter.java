package com.hmh.bookgenie.backend.jwt;


import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hmh.bookgenie.backend.dto.UserDetail;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter  extends UsernamePasswordAuthenticationFilter{

	//생성자 주입방식 @Autowired 추천하지않음
	private final AuthenticationManager authenticationManager;
	
	private final JWTUtil jwtUtil;
	
	
	
    public LoginFilter(AuthenticationManager authenticationManager,JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException{
		
		String userId = obtainUsername(request);
		String userPassword = obtainPassword(request);
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId,userPassword,null);
		return authenticationManager.authenticate(authToken);
	}
	
	//로그인 성공시 실행하는 메소드
	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

		UserDetail userDetail = (UserDetail)authentication.getPrincipal();
		
		String userId = userDetail.getUsername();
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        
        String userRole = auth.getAuthority();

        String token = jwtUtil.createJwt(userId, userRole, 60*60*10L);

        response.addHeader("Authorization", "Bearer " + token);
    }

	//로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

    	response.setStatus(401);
    }
}
