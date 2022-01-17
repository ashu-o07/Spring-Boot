package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;

@Component
public class Init {

	@Autowired
	EmployeeRepo em_repo;

	@EventListener
	public void appReady(ApplicationReadyEvent event) {

		em_repo.save(new Employee(1, "Ashish", "Designation"));
		em_repo.save(new Employee(2, "Abhishek", "SDE"));
		em_repo.save(new Employee(3, "Mukul", "Data Analyst"));
		em_repo.save(new Employee(4, "Dummy", "SDE"));
		em_repo.save(new Employee(5, "John", "Designation"));
		em_repo.save(new Employee(6, "Sahil", "Dummy"));
		em_repo.save(new Employee(7, "Tim", "Manager"));
		em_repo.save(new Employee(8, "Akash", "Designation"));
	}
}
