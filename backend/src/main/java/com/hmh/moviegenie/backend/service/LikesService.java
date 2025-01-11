package com.hmh.moviegenie.backend.service;

import org.springframework.stereotype.Service;

import com.hmh.moviegenie.backend.domain.Likes;
import com.hmh.moviegenie.backend.repository.LikesRepository;

@Service
public class LikesService {

	private final LikesRepository likesRepository;
	
	public LikesService(LikesRepository likesRepository) {
		this.likesRepository = likesRepository;
	}
	
	//좋아요 확인 기능
	public Boolean likeGet(String userId, Long movieId) {
		 return	likesRepository.existsByUser_UserIdAndMovie_MovieId(userId,movieId);
		
	}
	
	//좋아요 저장
	public void likePost(Likes newLikes) {
		likesRepository.save(newLikes);
		
	}
	
	//좋아요 삭제
	public void likeDelete(String userId, Long movieId) {
		likesRepository.deleteByUser_UserIdAndMovie_MovieId(userId,movieId);
		
	}

	
	
}
