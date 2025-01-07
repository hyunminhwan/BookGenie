package com.hmh.bookgenie.backend.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtHttpOnlyFilter extends OncePerRequestFilter{

	private final JWTUtil jwtUtil;
	
	public JwtHttpOnlyFilter(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	 @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        String token = null;

	        // 쿠키에서 JWT 가져오기
	        if (request.getCookies() != null) {
	            for (Cookie cookie : request.getCookies()) {
	                if ("authToken".equals(cookie.getName())) {
	                    token = cookie.getValue();
	                }
	            }
	        }

	        // JWT 검증
	        if (token != null && !jwtUtil.isExpired(token)) {
	            String username = jwtUtil.getUsername(token);
	            UsernamePasswordAuthenticationToken authentication =
	                    new UsernamePasswordAuthenticationToken(username, null, List.of());
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }

	        filterChain.doFilter(request, response);
	    }
}
