package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.ProductPropositionInformations;

public interface ProductPropositionInformationRepo extends JpaRepository<ProductPropositionInformations, String>{


	
}
