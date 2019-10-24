package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.models.RestrictionAge;

public interface RestrictionAgeRepo extends JpaRepository<RestrictionAge, String>{
	
	Optional<RestrictionAge> findById(String id);

}
