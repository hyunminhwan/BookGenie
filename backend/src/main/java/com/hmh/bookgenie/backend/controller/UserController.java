package com.hmh.bookgenie.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmh.bookgenie.backend.domain.Users;
import com.hmh.bookgenie.backend.dto.UserDto;
import com.hmh.bookgenie.backend.jwt.JWTUtil;
import com.hmh.bookgenie.backend.service.UserService;

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
}
