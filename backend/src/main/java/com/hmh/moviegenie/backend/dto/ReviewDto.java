package com.hmh.moviegenie.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDto {
	private Long reviewId;
	private Long movieId;
	private String userId;
	private String reviewContent;
	private Integer rating;
	private LocalDateTime createdAt;
}

