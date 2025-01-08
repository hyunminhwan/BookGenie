package com.hmh.moviegenie.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmh.moviegenie.backend.domain.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String>{

}
