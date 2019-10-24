package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Substitues {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String id_Product_To_Substitute;
	private String substitutedProduct;
	private Integer coeff;
	
	public Substitues() {
		
	}

	public String getId_Product_To_Substitute() {
		return id_Product_To_Substitute;
	}

	public void setId_Product_To_Substitute(String id_Product_To_Substitute) {
		this.id_Product_To_Substitute = id_Product_To_Substitute;
	}

	public String getSubstitutedProduct() {
		return substitutedProduct;
	}

	public void setSubstitutedProduct(String substitutedProduct) {
		this.substitutedProduct = substitutedProduct;
	}

	public Integer getCoeff() {
		return coeff;
	}

	public void setCoeff(Integer coeff) {
		this.coeff = coeff;
	}

	
	
	
}
