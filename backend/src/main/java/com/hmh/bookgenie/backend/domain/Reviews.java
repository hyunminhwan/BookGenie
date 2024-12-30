package com.hmh.bookgenie.backend.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "reviews")
public class Reviews {
	
	@Id
	@SequenceGenerator(	
			name="reviewseq",
			sequenceName = "review_no_seq",
			allocationSize = 1
			)
	@GeneratedValue(generator = "reviewseq")
    @Column(name = "review_id")
    private Long reviewId; // 리뷰 고유 ID

	@ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Books book; // 도서 객체 참조

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user; // 사용자 객체 참조

    @Column(name = "review_content")
    private String reviewContent; // 리뷰 내용

    @Column(name = "rating")
    private Integer rating; // 평점 (1~5)

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate; // 작성 시간
    
}
