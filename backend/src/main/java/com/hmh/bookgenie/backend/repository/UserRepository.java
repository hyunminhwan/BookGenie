package com.hmh.bookgenie.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmh.bookgenie.backend.domain.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String>{

	Users findByUserId(String UserId);
}
