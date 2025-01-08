package com.hmh.moviegenie.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmh.moviegenie.backend.domain.Users;
import com.hmh.moviegenie.backend.dto.UserDto;
import com.hmh.moviegenie.backend.jwt.JWTUtil;
import com.hmh.moviegenie.backend.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	
	private final UserService userService;
	private final JWTUtil jwtUtil;
	
	public UserController(UserService userService,JWTUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getUser(@CookieValue("authToken") String token){
		System.out.println(token);
		if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("쿠키가 없습니다.");
        }
		if (jwtUtil.isExpired(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired");
        }
		//jwt에서 사용자 id 추출
		String userId = jwtUtil.getUsername(token);

		//데이터베이스에서 사용자 정보 조회
		Users user = userService.getUser(userId);
		if(user ==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("유저가 없습니다.");
		}
		
		UserDto userDto =new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setUserEmail(user.getUserEmail());
		return ResponseEntity.ok(userDto);
		
	}
	
	@GetMapping("/Logout")
	public ResponseEntity<?> logout(HttpServletResponse response){
		// 쿠키 삭제
	    Cookie authCookie = new Cookie("authToken", null); // 값 제거
	    authCookie.setHttpOnly(true); // 동일한 속성 사용
	    authCookie.setSecure(false); // HTTPS 환경에서 true로 설정
	    authCookie.setPath("/"); // 설정된 경로와 동일해야 함
	    authCookie.setMaxAge(0); // 쿠키 즉시 삭제
	    response.addCookie(authCookie);
	    
	    return ResponseEntity.ok("로그아웃 되었습니다.");
	}
}
