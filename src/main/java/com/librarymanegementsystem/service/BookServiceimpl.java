package com.librarymanegementsystem.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanegementsystem.model.Book;
import com.librarymanegementsystem.repository.BookDao;

@Service
public class BookServiceimpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Override
	public Book addBook(Book book) {
		return bookDao.save(book);
	}

	@Override
	public Book updateBook(Book book) {

		Optional<Book> bookEntity = bookDao.findById(book.getBookId());
		if (bookEntity.isPresent()) {
			Book books = new Book();
			books.setBookId(book.getBookId());
			books.setPrice(book.getPrice());
			books.setAuthor(book.getAuthor());
			books.setBookName(book.getBookName());
			books.setCreatedDate(book.getCreatedDate());
			bookDao.save(books);
			return books;
		}
		return book;

	}

	@Override
	public Book getBookById(int id) {
		Optional<Book> bookEntity = bookDao.findById(id);

		if (bookEntity.isPresent()) {
			return bookEntity.get();
		} else
			throw new RuntimeException(" Vehicle not found for " + id);
	}

	@Override
	public void deleteBookById(int id) {

		this.bookDao.deleteById(id);
	}

	@Override
	public Book searchBook(String bookName) {
		// TODO Auto-generated method stub
		LocalDate date = LocalDate.now();

		Book book = bookDao.searchBook(bookName, date);
		return book;

	}

//	@Override
//	public Book searchBook(String bookName, Date  createdDate) {
//		Book book = bookDao.searchBook(bookName,createdDate);
//		return book;
//	}
}
