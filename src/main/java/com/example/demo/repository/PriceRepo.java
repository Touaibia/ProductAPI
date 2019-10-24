package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.example.demo.models.Price;

public interface PriceRepo extends CrudRepository<Price, Integer> {
	
	Optional<Price> findByProductID(String productID);
	Price findByPrix(float prix);
	
	
	
}
