package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Age;
import com.example.demo.models.Category;

public interface AgeRepo extends JpaRepository<Age, Integer>{

}
