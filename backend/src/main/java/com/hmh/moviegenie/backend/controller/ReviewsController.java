package com.hmh.moviegenie.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hmh.moviegenie.backend.dto.ReviewDto;
import com.hmh.moviegenie.backend.service.ReviewsServise;

@RestController
public class ReviewsController {

	private final ReviewsServise reviewsServise;
	
	public ReviewsController(ReviewsServise reviewsServise) {
		this.reviewsServise = reviewsServise;
	}
	
	@GetMapping("reviews")
	public ResponseEntity<List<ReviewDto>> getReviews(@RequestParam(name="movieId") Long movieId){
		List<ReviewDto> reviews = reviewsServise.getReviews(movieId);
		return ResponseEntity.ok(reviews);
	}
}
