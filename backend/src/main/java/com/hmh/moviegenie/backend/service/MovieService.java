package com.hmh.moviegenie.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmh.moviegenie.backend.domain.movies;
import com.hmh.moviegenie.backend.repository.MovieRepository;

@Service
public class MovieService {

	private final MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public List<movies> getMoviesList() {
		return movieRepository.findAll();
	}

	
}
