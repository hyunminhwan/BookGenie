package com.hmh.moviegenie.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmh.moviegenie.backend.domain.Likes;

import jakarta.transaction.Transactional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long>{

	Boolean existsByUser_UserIdAndMovie_MovieId(String userId, Long movieId);


	@Transactional
	void deleteByUser_UserIdAndMovie_MovieId(String userId, Long movieId);


}
