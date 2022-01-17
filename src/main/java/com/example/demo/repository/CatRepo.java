package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CatRepo extends JpaRepository<Category, Long> {

}
