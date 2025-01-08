package com.hmh.moviegenie.backend.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

//사용자 정보 테이블
@Data
@NoArgsConstructor
@Entity(name = "users")
public class Users {

 @Id
 @Column(name = "user_id")
 private String userId; // 사용자 고유번호

 @Column(name = "user_name")
 private String userName; // 사용자 이름

 @Column(name = "user_email", length = 100)
 private String userEmail; // 이메일

 @Column(name = "user_password", length = 255)
 private String userPassword; // 비밀번호
 
 @Column(name = "user_role")
 private String userRole; // 역할 (관리자, 일반 사용자)
 
 @Column(name = "created_at", nullable = false, updatable = false)
 private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시간
}
