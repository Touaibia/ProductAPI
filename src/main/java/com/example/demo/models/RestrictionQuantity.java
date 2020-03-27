package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RestrictionQuantity {

	@Id
	private String id;
	private String libelle;
	
	@OneToOne
	private Quantity quantityAttributes;
	
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
	public Quantity getQuantityAttributes() {
		return quantityAttributes;
	}
	public void setQuantityAttributes(Quantity quantityAttributes) {
		this.quantityAttributes = quantityAttributes;
	} 
	
	
	
	
	
	
}
