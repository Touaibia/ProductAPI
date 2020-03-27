package com.example.demo.repository;



import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Product;

public interface ProductRepo extends JpaRepository<Product, String>{


	
}
