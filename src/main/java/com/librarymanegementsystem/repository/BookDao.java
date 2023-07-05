package com.librarymanegementsystem.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.librarymanegementsystem.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

	@Query("SELECT book  FROM Book book where book.bookName=:bookName AND book.createdDate=:date")
	Book searchBook(@Param("bookName") String bookName,@Param("date") LocalDate date);

}
