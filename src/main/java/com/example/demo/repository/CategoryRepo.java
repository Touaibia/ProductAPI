package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Category;

public interface CategoryRepo extends JpaRepository<Category, String> {

	
}
