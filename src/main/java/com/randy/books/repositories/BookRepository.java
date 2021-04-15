package com.randy.books.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.randy.books.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findAll();
}

