package com.hmh.bookgenie.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    private Long likeId; // 좋아요 고유 ID

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user; // 좋아요를 누른 사용자

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Books book; // 좋아요가 눌린 도서
}
