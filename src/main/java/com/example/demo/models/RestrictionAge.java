package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RestrictionAge {

	@Id
	private String id;
	private String libelle;
	
	@OneToOne
	private Age ageAttribute;
	
	public RestrictionAge() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Age getAgeAttribute() {
		return ageAttribute;
	}

	public void setAgeAttribute(Age ageAttribute) {
		this.ageAttribute = ageAttribute;
	}

	
  
	
	
	
	
}
