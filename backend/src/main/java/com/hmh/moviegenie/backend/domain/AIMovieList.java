package com.hmh.moviegenie.backend.domain;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "ai_movie_list")
public class AIMovieList {

    @Id
    @SequenceGenerator(
        name = "ai_movie_list_seq",
        sequenceName = "ai_movie_list_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(generator = "ai_movie_list_seq")
    @Column(name = "list_id")
    private Long listId; //ai 추천 고유번호

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user; // 사용자 객체 

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private movies movie; // 영화 객체 

    @Column(nullable = false)
    private Float score; // 추천 점수 (AI 알고리즘이 계산한 값)
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시간
}
