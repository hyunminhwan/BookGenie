package com.hmh.bookgenie.backend.domain;

import java.time.LocalDateTime;

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
@Entity(name = "ai_book_list") // 테이블 이름도 변경
public class AIBookList {

    @Id
    @SequenceGenerator(
        name = "ai_book_list_seq",
        sequenceName = "ai_book_list_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(generator = "ai_book_list_seq")
    @Column(name = "list_id")
    private Long listId; // 추천 기록 고유 ID

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user; // 추천 대상 사용자 객체 참조

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Books book; // 추천된 도서 객체 참조

    @Column(name = "ai_content")
    private String aiContent; // 추천 이유

    @Column(name = "created_date")
    private LocalDateTime createdDate; // 추천 생성 시간
}
