package com.hmh.bookgenie.backend.domain;

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
 private Long userId; // 사용자 고유번호

 @Column(name = "user_name")
 private String userName; // 사용자 이름

 @Column(name = "user_email")
 private String userEmail; // 이메일

 @Column(name = "user_password")
 private String userPassword; // 비밀번호

 @Column(name = "user_role")
 private String userRole; // 역할 (관리자, 일반 사용자)
}
