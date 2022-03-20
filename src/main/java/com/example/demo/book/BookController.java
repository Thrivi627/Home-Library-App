package com.example.demo.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping("/books/new")
	public String showNewBookForm(Model model) {
		List<Category> listCategories = categoryRepo.findAll();
		
		model.addAttribute("book", new Book());
		model.addAttribute("listCategories", listCategories);
		
		return "book_form";
	}
	
	@PostMapping("/books/save")
	public String saveBook(Book book, HttpServletRequest request) {
		String[] detailIDs = request.getParameterValues("detailID");
		String[] detailNames = request.getParameterValues("detailName");
		String[] detailValues = request.getParameterValues("detailValue");
		
		for (int i = 0; i < detailNames.length; i++) {
			if (detailIDs != null && detailIDs.length > 0) {
				book.setDetail(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);
			}else {
			book.addDetail(detailNames[i], detailValues[i]);
			}
		}
		
		bookRepo.save(book);
		
		return "redirect:/books";	
	}
	
	@GetMapping("/books")
	public String listBooks(Model model) {
		List<Book> listBooks = bookRepo.findAll();
		model.addAttribute("listBooks", listBooks);
		
		return "books";
	}
	
	@GetMapping("books/edit/{id}")
	public String showEditBookForm(@PathVariable("id") Integer id, Model model) {
		Book book = bookRepo.findById(id).get();
		model.addAttribute("book", book);
		
List<Category> listCategories = categoryRepo.findAll();
		
		model.addAttribute("listCategories", listCategories);
		
		return "book_form";
	}
	
	@GetMapping("books/delete/{id}")
	public String deleteBook(@PathVariable("id") Integer id, Model model) {
		bookRepo.deleteById(id);
		
		return "redirect:/books";
		
	}

}
