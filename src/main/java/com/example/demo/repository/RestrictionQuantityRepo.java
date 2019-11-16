package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.RestrictionQuantity;

public interface RestrictionQuantityRepo extends JpaRepository<RestrictionQuantity, String> {
	
	@Override
	default Optional<RestrictionQuantity> findById(String id) {
		return null;
	}

}
