package com.hmh.moviegenie.backend.domain;

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
@Entity(name = "likes")
public class Likes {

    @Id
    @SequenceGenerator(	
			name="likeseq",
			sequenceName = "like_no_seq",
			allocationSize = 1
			)
	@GeneratedValue(generator = "likeseq")
    @Column(name = "like_id")
    private Long likeId; //좋아요 고유번호

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user; //사용자 객체

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
    private Movies movie; //영화,드라마 객체
}
