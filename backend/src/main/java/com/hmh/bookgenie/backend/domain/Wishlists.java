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
@Entity(name = "wishlists")
public class Wishlists {

    @Id
    @SequenceGenerator(	
			name="wishlistseq",
			sequenceName = "wishlist_no_seq",
			allocationSize = 1
			)
	@GeneratedValue(generator = "wishlistseq")
    @Column(name = "wishlist_id")
    private Long wishlistId; // 위시리스트 고유 ID

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Users user; // 위시리스트를 생성한 사용자

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Books book; // 위시리스트에 추가된 도서
}
