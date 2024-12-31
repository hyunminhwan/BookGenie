package com.hmh.bookgenie.backend.domain;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "bookrentals")
public class BookRentals {

	@Id
    @Column(name = "rental_id")
    private Long rentalId; // 대여기록 고유 번호

	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user; // 사용자 객체

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Books book; // 도서 객체

    @Column(name = "borrowed_date")
    @Temporal(TemporalType.DATE)
    private Date borrowedDate; // 대여 날짜

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate; // 반납 날짜

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private BookStatus status; // 상태 (대여 중, 반납 완료 등)

    @Column(name = "overdue_fee")
    private Double overdueFee; // 연체료
	
}
