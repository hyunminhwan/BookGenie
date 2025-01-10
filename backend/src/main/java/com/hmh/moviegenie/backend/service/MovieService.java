package com.hmh.moviegenie.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hmh.moviegenie.backend.domain.Movies;
import com.hmh.moviegenie.backend.repository.MovieRepository;

@Service
public class MovieService {

	private final MovieRepository movieRepository;
	
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	//영화,드라마 리스트
	public List<Movies> getMoviesList() {
		return movieRepository.findAll();
	}

	//영화,드라마 저장
	public void save(Movies movie) {
		 movieRepository.save(movie);
	}

	//상세페이지
	public Movies getMovie(Long movieId) {
		Optional<Movies> movie = movieRepository.findById(movieId);
		if(movie.isPresent()) {
			return movie.get();
		}
		return null;
	}

	
}
