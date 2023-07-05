package com.librarymanegementsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanegementsystem.model.Book;
import com.librarymanegementsystem.service.BookService;

@RestController
public class Bookcontroller {

	@Autowired
	private BookService bookService;

	@PostMapping("/saveBook")
	public ResponseEntity<Book> saveAddressHandler(@Valid @RequestBody Book add) {
		Book savedAddress = bookService.addBook(add);
		return new ResponseEntity<Book>(savedAddress, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	public ResponseEntity<Book> UpdateVehicleRegistraton(@RequestBody Book book) {

		Book bookupdated = bookService.updateBook(book);

		return new ResponseEntity<Book>(bookupdated, HttpStatus.OK);

	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getVehicleById(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);

		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	@DeleteMapping("/book/{id}")
	public String deleteVehicle(@PathVariable("id") int id) {
		this.bookService.deleteBookById(id);
		return " Record Deleted Succefully";

	}

	@GetMapping("/search")
	public ResponseEntity<Book> searchBook(@RequestParam(value = "bookName", required = false) String bookName) {

		Book searchBook = bookService.searchBook(bookName);

		return new ResponseEntity<Book>(searchBook, HttpStatus.OK);
	}
}
