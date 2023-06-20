package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository brepository;

	@Autowired
	private CategoryRepository crepository;
	
    @RequestMapping(value="/login")
	public String login() {
		return "login";
		
	}

	// Home page list of books
    @RequestMapping(value={"/", "/home"})
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "index";
	}

	// Add book
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	@PostMapping(value = "/save")
	public String save(Book book) {
		brepository.save(book);
		return "redirect:home";
	}

	// Deleting Book
	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../"; // double dot because the delete must
	}

	@RequestMapping(value = "/modify/{id}")
	public String modifyBook(@PathVariable("id") Long bookId, Model model) {
		Optional<Book> book = brepository.findById(bookId); // optional means there might be a book with the ID or not
		model.addAttribute("categories", crepository.findAll());
		model.addAttribute("book", book);
		return "modifybook";
	}

	// REST service to list all the books
	@GetMapping("/booklist")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) brepository.findAll();
	}

	// REST service to get book by ID
	@RequestMapping(value = "/booklist/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return brepository.findById(bookId);
	}
//curl -X POST localhost:8080/api/books -H 'Content-type:application/json' -d '{"title": "Siddhartha", "author": "Hermann Hesse", "year": "1922", "isbn": "9766655", "price":"20.0", "category":""}'


}
