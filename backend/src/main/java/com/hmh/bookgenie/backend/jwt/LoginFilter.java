package com.hmh.bookgenie.backend.jwt;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmh.bookgenie.backend.dto.UserDetail;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
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
		   String username = null;
		    String password = null;

		    if (request.getContentType() != null && request.getContentType().contains("application/json")) {
		        try {
		            // JSON 요청 파싱
		            StringBuilder sb = new StringBuilder();
		            String line;
		            BufferedReader reader = request.getReader();
		            while ((line = reader.readLine()) != null) {
		                sb.append(line);
		            }
		            ObjectMapper mapper = new ObjectMapper();
		            Map<String, String> jsonRequest = mapper.readValue(sb.toString(), Map.class);

		            username = jsonRequest.get("username");
		            password = jsonRequest.get("password");
		        } catch (IOException e) {
		            throw new RuntimeException("Failed to parse JSON request", e);
		        }
		    } else {
		        // URL-encoded 처리
		        username = obtainUsername(request);
		        password = obtainPassword(request);
		    }

		    if (username == null || password == null) {
		        throw new AuthenticationException("Username or Password is missing") {};
		    }

		    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
		    
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

        long jwtExpiry = 2*60*60*1000L; //밀리초
        String token = jwtUtil.createJwt(userId, userRole, jwtExpiry);

        int cookieExpiry = (int) (jwtExpiry / 1000); //초단위
        Cookie authCookie = new Cookie("authToken", token);
        authCookie.setHttpOnly(true); // 자바스크립트 접근 차단
        authCookie.setSecure(false); // HTTPS 환경에서는 true로 설정
        authCookie.setPath("/");
        authCookie.setMaxAge(cookieExpiry); 
        response.addCookie(authCookie);
    }

	//로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

    	response.setStatus(401);
    }
}
