package com.hmh.moviegenie.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hmh.moviegenie.backend.domain.Reviews;
import com.hmh.moviegenie.backend.dto.ReviewDto;
import com.hmh.moviegenie.backend.repository.ReviewsRepository;

@Service
public class ReviewsServise {

	private final ReviewsRepository reviewsRepository;
	
	private ReviewsServise(ReviewsRepository reviewsRepository) {
		this.reviewsRepository = reviewsRepository;
	}
	
	public List<ReviewDto> getReviews(Long movieId) {
		List<Reviews> reviewsList = reviewsRepository.findAllByMovie_MovieId(movieId);
		
		if(!reviewsList.isEmpty()) {
			List<ReviewDto> reviewDto = new ArrayList<>();
			for(Reviews review:reviewsList) {
				reviewDto.add(new ReviewDto(
						review.getReviewId(),
						review.getMovie().getMovieId(),
						review.getUser().getUserId(),
						review.getReviewContent(),
						review.getRating(),
						review.getCreatedAt()
						));
				
			}
			return reviewDto;
		}
		return null;
	}

}
