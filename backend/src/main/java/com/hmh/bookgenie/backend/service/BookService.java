package com.hmh.bookgenie.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hmh.bookgenie.backend.domain.Books;
import com.hmh.bookgenie.backend.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	public List<Books> getBooksList() {
		return bookRepository.findAll();
	}

	
}
