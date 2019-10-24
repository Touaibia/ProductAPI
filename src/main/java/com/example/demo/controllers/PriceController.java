package com.example.demo.controllers;

import javax.validation.Valid;

import org.aspectj.lang.annotation.RequiredTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Price;
import com.example.demo.services.*;
import com.example.demo.util.CustomError;

@RestController
@RequestMapping("/price-api")
public class PriceController {
	
	@Autowired
	private PriceService PriceService;
	private String ListOfUnits;
	
	private Float floatObj = new Float(0.0f);
	
    public static final Logger logger = LoggerFactory.getLogger(PriceController.class);

	
   
	public PriceController() {
		
	}
	
	// --------------------------------------------- Get Price -----------------------------------------------------------
	 @RequestMapping(value="/get-price/{product_ID}",method = RequestMethod.GET)
	 public ResponseEntity<Price> getPrice(@PathVariable("product_ID") String product_ID){
		logger.info("Retrait du prix correspondant au produit : ",product_ID);
		Price prix = new Price();
		prix.setProductID(product_ID);
		if(!this.PriceService.existPrice(prix)) {
			logger.error("Le produit correspondant n'existe pas");
			return new ResponseEntity(new CustomError("Le produit correspondant n'existe pas"), HttpStatus.NOT_FOUND);
		}
		 return new ResponseEntity<Price>(this.PriceService.getPrice(prix), HttpStatus.OK); 
	 }
	 
	 
	 // --------------------------------------------------- Create a price -------------------------------------------
	 @RequestMapping(value="/{productID}/create-price", method= RequestMethod.POST)
	 public ResponseEntity<Price> createPrice(@RequestBody  Price prix,@PathVariable("productID") String productID){
			
		 if(!prix.getProductID().equals(productID)) {
			 logger.error("Un conflit dans l'id du produit (Body/URL)",prix.getProductID());
				return new ResponseEntity(new CustomError("Un conflit dans l'id du produit (Body / URL)"), HttpStatus.CONFLICT);
		 }
			 
		if(this.PriceService.existPrice(prix)) {
			logger.error("Ce produit {} à déja un prix, il peut-etre modifier ou supprimer",prix.getProductID());
			return new ResponseEntity(new CustomError("Ce produit à déja un prix, il peut-etre modifier ou supprimer"), HttpStatus.CONFLICT);
		}
		if(prix.getPrix() == floatObj) {
			logger.error("Le prix du produit est null, faudrait renseigné une valeur pour le prix",prix.getProductID());
			return new ResponseEntity(new CustomError("Le prix du produit est null, faudrait renseigné une valeur pour le prix"), HttpStatus.NOT_ACCEPTABLE);
		}

		if(prix.getUnit().isEmpty()) {
			logger.error("Unité vide, veuiller renseigné l'unité du prix",prix.getProductID());
			return new ResponseEntity(new CustomError("Unité vide, veuiller renseigné l'unité du prix"), HttpStatus.NOT_ACCEPTABLE);

		}
		
		if(!this.PriceService.checkUnit(prix.getUnit())) {
			logger.error("Unité inattendu, veuiller renseigné l'unité du prix",prix.getProductID());
			ListOfUnits = this.PriceService.getUnits();
			return new ResponseEntity(new CustomError("Veuiller renseigné une unité parmi : "+ListOfUnits), HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		 Price CurrentPrice = this.PriceService.addPrice(prix);
		 return new ResponseEntity<Price>(CurrentPrice, HttpStatus.CREATED);
		 
	 }

	 // --------------------------------------------------- Update a price -------------------------------------------
	 @RequestMapping(value="/{productID}/update-price",method=RequestMethod.PUT)
	 public ResponseEntity <Price> updatePrice(@RequestBody Price prix, @PathVariable("productID") String productID){
		
		 if(!prix.getProductID().equals(productID)) {
			 logger.error("Un conflit dans l'id du produit (Body/URL)",prix.getProductID());
				return new ResponseEntity(new CustomError("Un conflit dans l'id du produit (Body / URL)"), HttpStatus.CONFLICT);
		 }
		 if(!this.PriceService.existPrice(prix)) {
				logger.error("Ce produit {} n'a jamais eu de  prix, il faut d'abord créer un  prix pour ce produit",prix.getProductID());
				return new ResponseEntity(new CustomError("Ce produit n'a jamais eu de  prix, il faut créer d'abord un prix pour ce produit"), HttpStatus.CONFLICT);
			}
		 if(prix.getPrix() == floatObj) {
				logger.error("Le prix du produit est null, faudrait renseigné une valeur pour le prix",prix.getProductID());
				return new ResponseEntity(new CustomError("Le prix du produit est null, faudrait renseigné une valeur pour le prix"), HttpStatus.NOT_ACCEPTABLE);
			}
		 if(prix.getUnit().isEmpty()) {
				logger.error("Unité vide, veuiller renseigné l'unité du prix",prix.getProductID());
				return new ResponseEntity(new CustomError("Unité vide, veuiller renseigné l'unité du prix"), HttpStatus.NOT_ACCEPTABLE);
			}
		 
		if(!this.PriceService.checkUnit(prix.getUnit())) {
				logger.error("Unité inattendu, ",prix.getProductID());
				ListOfUnits = this.PriceService.getUnits();
				return new ResponseEntity(new CustomError("Veuiller renseigné une unité parmi : "+ListOfUnits), HttpStatus.NOT_ACCEPTABLE);
			}
		 Price priceToUpdate = new Price();
		 priceToUpdate.setProductID(productID);
		 priceToUpdate.setPrix(prix.getPrix());
		 priceToUpdate.setUnit(prix.getUnit());
		 
		 Price CurrentPrice = this.PriceService.addPrice(priceToUpdate);
		 return new ResponseEntity<Price>(CurrentPrice, HttpStatus.OK);
		 
	 }
	 
}
