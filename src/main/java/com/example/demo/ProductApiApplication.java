package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.controllers.QuantityRestrictionGrController;
import com.example.demo.services.CategoryServiceImpl;

@SpringBootApplication
public class ProductApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApiApplication.class, args);
		
	}
	
	

}
