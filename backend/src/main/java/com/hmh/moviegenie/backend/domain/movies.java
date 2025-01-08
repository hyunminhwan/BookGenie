package com.hmh.moviegenie.backend.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

//영화기본정보 테이블
@Data
@NoArgsConstructor
@Entity(name = "movies")
public class movies {
	
	@Id
	@SequenceGenerator(	
			name="movieseq",
			sequenceName = "movie_no_seq",
			allocationSize = 1
			)
	@GeneratedValue(generator = "movieseq")
	@Column(name = "movie_id")
	private Long movieId;//영화,드라마번호
	
	@Column(name = "movie_name")
	private String movieName;//제목
	
	@Column(name = "movie_content")
	private String movieContent;//설명
	
	@Column(name = "release_date")
	private LocalDate releaseDate; //개봉일
	
	@Column(length = 100)
    private String director; // 감독 이름
	
	@Column(name = "ott_link")
    private String ottLink; // OTT 플랫폼으로 이동할 수 있는 링크
	
	@Column(name="img_url")
	private String imgUrl; //이미지 경로+이름
	
	@Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시간
	
}
