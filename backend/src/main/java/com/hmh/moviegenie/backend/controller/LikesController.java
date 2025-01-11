package com.hmh.moviegenie.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hmh.moviegenie.backend.domain.Likes;
import com.hmh.moviegenie.backend.service.LikesService;

@RestController
@RequestMapping("/likes")
public class LikesController {

	private final LikesService likesService;
	
	public LikesController(LikesService likesService) {
		this.likesService = likesService;
	}
	
	@GetMapping("/get")
	public ResponseEntity<Boolean> likeGet(@RequestParam(name="userId") String userId,@RequestParam(name="movieId") Long movieId) {
		Boolean like = likesService.likeGet(userId,movieId);
		 return ResponseEntity.ok(like);
	}
	
	@PostMapping("/post")
	public ResponseEntity<String> likePost(@RequestBody Likes newLikes) {
		 likesService.likePost(newLikes);
        return ResponseEntity.ok("좋아요가 저장되었습니다.");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> likeDelete(@RequestParam(name="userId") String userId,@RequestParam(name="movieId") Long movieId){
		likesService.likeDelete(userId,movieId);
        return ResponseEntity.ok("좋아요가 저장되었습니다.");
	}
}
