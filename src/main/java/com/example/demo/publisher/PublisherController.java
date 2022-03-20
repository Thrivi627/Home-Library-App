package com.example.demo.publisher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;

@Controller
public class PublisherController {

	@Autowired
	private PublisherRepository publisherRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping("/publishers/new")
	public String showCreateNewPublisherForm(Model model) {
		List<Category> listCategories = categoryRepo.findAll();
		
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("publisher", new Publisher());
		
		return "publisher_form";
	}
	
	@PostMapping("/publishers/save")
	public String savePublisher(Publisher publisher) {
		publisherRepo.save(publisher);
		
		return "redirect:/publishers";
	}
	
	@GetMapping("/publishers")
	public String listPublishers(Model model) {
		List<Publisher> listPublishers = publisherRepo.findAll();
		model.addAttribute("listPublishers", listPublishers);
		return "publishers";
	}
	
	@GetMapping("publishers/edit/{id}")
	public String showEditPublisherForm(@PathVariable("id") Integer id, Model model) {
		List<Category> listCategories = categoryRepo.findAll();
		Publisher publisher = publisherRepo.findById(id).get();
		
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("publisher", publisher);
		return "publisher_form";
	}
	
}
