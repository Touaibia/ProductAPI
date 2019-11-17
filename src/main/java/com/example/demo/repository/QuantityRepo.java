package com.example.demo.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Age;
import com.example.demo.models.Quantity;

public interface QuantityRepo extends JpaRepository<Quantity, Integer>{
	
	Optional<Quantity> findByValueAndUnite(Float value, String unite);

}
