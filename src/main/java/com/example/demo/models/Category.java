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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String iD;
	
	private String libelle;
	private Date dateDebut;
	private Date dateFin;
	
	@OneToOne
	private Category categoryParent;
	
	private ArrayList<Category> categoryFils;
	private ArrayList<ProductPropositionInformations> listProduct;
	
	public Category() {
		
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		libelle = libelle;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		dateFin = dateFin;
	}

	public Category getCategoryParent() {
		return categoryParent;
	}

	public void setCategoryParent(Category categoryParent) {
		categoryParent = categoryParent;
	}

	public ArrayList<Category> getCategoryFils() {
		return categoryFils;
	}

	public void setCategoryFils(ArrayList<Category> categoryFils) {
		categoryFils = categoryFils;
	}

	public ArrayList<ProductPropositionInformations> getListProduct() {
		return listProduct;
	}

	public void setListProduct(ArrayList<ProductPropositionInformations> listProduct) {
		listProduct = listProduct;
	}
	
	
}
