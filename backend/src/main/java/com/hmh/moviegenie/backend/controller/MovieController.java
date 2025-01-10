package com.hmh.moviegenie.backend.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hmh.moviegenie.backend.domain.Movies;
import com.hmh.moviegenie.backend.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;
	
	private static final String UPLOAD_DIR = "C:\\movie";
	
	public MovieController(MovieService movieService) {
		this.movieService=movieService;
	}
	@GetMapping("/list")
	public  List<Movies> getMoviesList() {
		return movieService.getMoviesList();
	}
	
	@GetMapping("/detail/{movieId}")
	public Movies getMovie(@PathVariable(name = "movieId") Long movieId) {
		return movieService.getMovie(movieId);
	}
	
	@PostMapping("/insert")
    public ResponseEntity<Map<String, String>> uploadMovie(
            @RequestParam("file") MultipartFile file,
            @RequestParam Map<String, String> movieData
    ) {
        Map<String, String> response = new HashMap<>();
        try {
            // 파일 저장
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = UPLOAD_DIR + File.separator + fileName;
            File saveFile = new File(filePath);
            file.transferTo(saveFile);

            // 데이터베이스 저장
            Movies movie = new Movies();
            movie.setMovieName(movieData.get("movieName"));
            movie.setMovieContent(movieData.get("movieContent"));
            movie.setReleaseDate(LocalDate.parse(movieData.get("releaseDate")));
            movie.setDirector(movieData.get("director"));
            movie.setOttLink(movieData.get("ottLink"));
            movie.setOttName(movieData.get("ottName"));
            movie.setGenre(movieData.get("genre"));
            movie.setMovieType(movieData.get("movieType"));
            movie.setImgUrl(fileName); // DB에는 상대 경로 저장

            movieService.save(movie);

            response.put("message", "영화/드라마 등록 성공!");
            response.put("filePath", filePath);
        } catch (IOException e) {
            response.put("message", "파일 업로드 실패!");
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }
}
