package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Substitues;

public interface SubstituesRepo extends JpaRepository<Substitues, String>{
	

}
