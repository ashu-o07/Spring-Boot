package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.AssetRepo;
import com.example.demo.model.*;
import com.example.demo.repository.EmployeeRepo;

@Controller
public class HomeController {

	@Autowired
	EmployeeRepo em_repo;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Employee> listEmp = em_repo.findAll();
		model.addAttribute("listEmp", listEmp);

		return "index"; //  src/main/resources/templates/index.html
	}
}
