package com.hmh.moviegenie.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmh.moviegenie.backend.domain.Likes;

import jakarta.transaction.Transactional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long>{

	Boolean existsByUser_UserIdAndMovie_MovieId(String userId, Long movieId);


	@Transactional
	void deleteByUser_UserIdAndMovie_MovieId(String userId, Long movieId);


	List<Likes> findAllByUser_UserId(String userId);


}
