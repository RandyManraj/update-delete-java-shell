package com.randy.books.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.randy.books.models.Book;
import com.randy.books.services.BookService;

@Controller
public class BookController {
	
private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	@RequestMapping("/")
	public String home(Model model, @ModelAttribute("book") Book book) {
		this.bookService.allBooks();
		
		model.addAttribute("allBooks", this.bookService.allBooks());
		return "index.jsp";
	}
	
	@PostMapping("/")
	public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "index.jsp";
			
		}else {
			this.bookService.createBook(book);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/edit/{bookId}")
	public String editBook(@PathVariable("bookId")Long id, Model model) {
		Book book = this.bookService.getBook(id);
		model.addAttribute("book", book);
		return "edit.jsp";
	}
	@RequestMapping(value = "/edit/{bookId}/update", method= RequestMethod.POST)
	public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, @PathVariable("bookId") Long id) {

		book.setId(id);
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			this.bookService.updateBook(book);
			return "redirect:/";
		}
		
	}
	@RequestMapping(value="/delete/{id}")
	public String destroy(@PathVariable("id")Long id) {
		this.bookService.deleteBook(id);
		return "redirect:/";
	}

}
