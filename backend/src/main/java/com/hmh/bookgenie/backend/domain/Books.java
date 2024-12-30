package com.hmh.bookgenie.backend.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

//도서기본정보 테이블
@Data
@NoArgsConstructor
@Entity(name = "books")
public class Books {
	
	@Id
	@SequenceGenerator(	
			name="bookseq",
			sequenceName = "book_no_seq",
			allocationSize = 1
			)
	@GeneratedValue(generator = "bookseq")
	@Column(name = "book_id")
	private Long bookId;//도서고유번호
	
	@Column(name = "book_content")
	private String bookContent;//책이름
	
	@Column(name = "book_detail")
	private String bookDetail;//도서설명
	
	@Column(name = "book_isbn")
	private String bookIsbn;//ISBN번호
	
	@Column(name = "book_date")
	@Temporal(TemporalType.DATE)
	private Date bookDate;//출판일
	
	@Column(name = "available")
	private Boolean available;//대여 가능 여부
	
	@Column(name="status")
	@Enumerated(EnumType.ORDINAL)//숫자로 DB에 저장
	private BookStatus status; //대여가능여부
	
	
	
}
