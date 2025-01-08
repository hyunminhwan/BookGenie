package com.hmh.moviegenie.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmh.moviegenie.backend.domain.movies;
import com.hmh.moviegenie.backend.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService=movieService;
	}
	@GetMapping("/list")
	public  List<movies> getMoviesList() {
		return movieService.getMoviesList();
	}
}
