package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Product implements Serializable{
	
	@Id
	private String id;
	
	private String libelle;
	private String description;
	private Long ean;
	private String image;
	private String poid;
	private String hauteur;
	private String largeur;
	private String longueur;
	private Boolean surgeler;
	
	@OneToOne
	private RestrictionAge RestrictionAge;
	
	@OneToOne
	private RestrictionQuantity RestrictionAuantity;
	
	
	public Product() {
		
	}
	
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		description = description;
	}
	public Long getEAN() {
		return ean;
	}
	public void setEAN(Long eAN) {
		ean = eAN;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		image = image;
	}
	public String getPoid() {
		return poid;
	}
	public void setPoid(String poid) {
		poid = poid;
	}
	public String getHauteur() {
		return hauteur;
	}
	public void setHauteur(String hauteur) {
		hauteur = hauteur;
	}
	public String getLargeur() {
		return largeur;
	}
	public void setLargeur(String largeur) {
		this.largeur = largeur;
	}
	public String getLongueur() {
		return longueur;
	}
	public void setLongueur(String longueur) {
		longueur = longueur;
	}
	public Boolean getSurgeler() {
		return surgeler;
	}
	public void setSurgeler(Boolean surgeler) {
		surgeler = surgeler;
	}


	public RestrictionAge getRestrictionAge() {
		return RestrictionAge;
	}


	public void setRestrictionAge(RestrictionAge restrictionAge) {
		RestrictionAge = restrictionAge;
	}


	public RestrictionQuantity getRestrictionAuantity() {
		return RestrictionAuantity;
	}


	public void setRestrictionAuantity(RestrictionQuantity restrictionAuantity) {
		RestrictionAuantity = restrictionAuantity;
	}
	
	

}
