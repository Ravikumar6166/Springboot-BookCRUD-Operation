package com.librarymanegementsystem.service;

import org.springframework.data.repository.query.Param;

import com.librarymanegementsystem.model.Book;

public interface BookService {

	public Book addBook(Book book);
	
	public Book updateBook(Book book);
	
	Book getBookById(int id);
	
	public void deleteBookById(int id);
	
	Book searchBook(@Param("bookName")String bookName);
}
