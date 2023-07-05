package com.librarymanegementsystem.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;
	
	private int price;
	
	private String author;
	
	private String bookName;
	
	private LocalDate createdDate;

	public Book(int bookId, int price, String author, String bookName, LocalDate createdDate) {
		super();
		this.bookId = bookId;
		this.price = price;
		this.author = author;
		this.bookName = bookName;
		this.createdDate = createdDate;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	
	
	
	
	
}
