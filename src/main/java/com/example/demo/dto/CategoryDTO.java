package com.example.demo.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class CategoryDTO {

	@NotNull
	@Id
	private String categoryId;
	
	@NotNull
	private String libelle;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	

}
