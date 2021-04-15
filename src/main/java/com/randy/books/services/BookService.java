package com.randy.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.randy.books.models.Book;
import com.randy.books.repositories.BookRepository;


@Service
public class BookService {
    // adding the book repository as a dependency
    private final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository) {
    	this.bookRepository = bookRepository;
    }
    
    public List<Book> allBooks(){
    	return this.bookRepository.findAll();
    }
    

    public Book createBook(Book l) {
    	return this.bookRepository.save(l);
    }

    public Book getBook(Long id) {
    	return this.bookRepository.findById(id).orElse(null);

    }
    
    public Book updateBook(Book book) {
    	return this.bookRepository.save(book);
    }
    
    public void deleteBook(Long id) {
    	this.bookRepository.deleteById(id);
    }
    public Book getOneBook(Long id) {
    	Optional<Book> optionalBook = this.bookRepository.findById(id);
    	if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
}