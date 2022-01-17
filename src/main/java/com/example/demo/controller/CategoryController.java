package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.CatRepo;
import com.example.demo.model.Asset;
import com.example.demo.model.Category;
import com.example.demo.model.Employee;

@Controller
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CatRepo cat_repo;

	@GetMapping("/category")
	public String getAllCategories(Model model) {
		Category c = new Category();

		List<Category> cat = cat_repo.findAll();

		model.addAttribute("category", c);

		model.addAttribute("cat", cat);
		model.addAttribute("identifyPage", 0);

		return "category"; // src/main/resources/templates/category.html
	}

	// Adding a new category
	@PostMapping("/category")
	public String addcategory(Category category) {

		cat_repo.save(category);
		return "redirect:/api/category";

	}
	
	// **************************************** Update category methods/api starts************************************
	@GetMapping("/category/{id}")
	public String updateCategoryForm(@PathVariable Long id, Model model) {

		Optional<Category> c = cat_repo.findById(id);

		List<Category> cat = cat_repo.findAll();

		model.addAttribute("cat", cat);

		model.addAttribute("identifyPage", 1);
		model.addAttribute("category", c.get());

		return "category";

	}

	@PostMapping("/UpdateCategory")
	public String updateCategory(@ModelAttribute("category") Category category) {

		cat_repo.save(category);

		return "redirect:/api/category";

	}
	
	// **************************************** Update category methods/api ends************************************

}
