package com.example.demo.models;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Category {

	@Id
	private String categoryId;
	
	private String libelle;
	private String dateDebut;
	private String dateFin;
	
	@OneToOne
	private Category categoryParent;
	
	private ArrayList<Category> categoryFils;
	private ArrayList<Product> listProduct;
	
	public Category() {
		
	}
	
	

	public String getCategoryId() {
		return this.categoryId;
	}



	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}



	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Category getCategoryParent() {
		return this.categoryParent;
	}

	public void setCategoryParent(Category categoryParent) {
		this.categoryParent = categoryParent;
	}

	public ArrayList<Category> getCategoryFils() {
		return this.categoryFils;
	}

	public void setCategoryFils(ArrayList<Category> categoryFils) {
		this.categoryFils = categoryFils;
	}

	public ArrayList<Product> getListProduct() {
		return this.listProduct;
	}

	public void setListProduct(ArrayList<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
	
}
