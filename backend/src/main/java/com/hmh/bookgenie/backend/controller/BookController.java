package com.hmh.bookgenie.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hmh.bookgenie.backend.domain.Books;
import com.hmh.bookgenie.backend.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService=bookService;
	}
	@GetMapping("/list")
	public  List<Books> getBooksList() {
		return bookService.getBooksList();
	}
}
