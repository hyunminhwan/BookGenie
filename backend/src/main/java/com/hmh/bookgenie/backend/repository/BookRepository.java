package com.hmh.bookgenie.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmh.bookgenie.backend.domain.Books;

@Repository
public interface BookRepository extends JpaRepository<Books,Long > {

	
}
