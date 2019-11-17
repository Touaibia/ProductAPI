package com.example.demo.controllers;

import java.util.Optional;

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

import com.example.demo.models.RestrictionAge;
import com.example.demo.models.RestrictionQuantity;
import com.example.demo.services.RestrictionAgeService;
import com.example.demo.services.RestrictionQuantityService;
import com.example.demo.util.CustomError;

@RestController
public class QuantityRestrictionGrController {

	@Autowired
	private RestrictionQuantityService quantityRestrictionService;
	public static final Logger logger = LoggerFactory.getLogger(QuantityRestrictionGrController.class);
	
	

	// --------------------------------------------------- Create a RestrictionQuantityGroupe -------------------------------------------
	@RequestMapping(value="/{QuantityRestrictiontID}/create-quantityrestrictiongroupe", method=RequestMethod.POST)
    public ResponseEntity<RestrictionQuantity> createQuantityRestrictionGroupe(@RequestBody  RestrictionQuantity restrictionQuantity,@PathVariable("QuantityRestrictiontID") String quantityRestricitionID){
		
		if(quantityRestricitionID.isEmpty() || restrictionQuantity.getId().isEmpty() || !restrictionQuantity.getId().equals(quantityRestricitionID)) {
			 logger.error("Un conflit dans l'id {} (Body/URL) ",restrictionQuantity.getId());
			 return new ResponseEntity(new CustomError("Un conflit dans l'id "+restrictionQuantity.getId()+" (Body/URL)"), HttpStatus.CONFLICT);
		}
		if(this.quantityRestrictionService.existQuantityRestrictionGR(restrictionQuantity)) {
			logger.error("Ce groupe de restriction {} existe déja",restrictionQuantity.getId());
			return new ResponseEntity(new CustomError("Ce groupe de restriction "+restrictionQuantity.getId()+" existe déja"), HttpStatus.CONFLICT);
		}
		
		if(restrictionQuantity.getQuantityAttributes().getValue().equals(null) ||(restrictionQuantity.getQuantityAttributes().getUnite().isEmpty())){
			logger.error("Veuillez bien renseigne les valeurs value/unite");
			return new ResponseEntity(new CustomError("Veuillez bien renseigne les valeurs value/unité"), HttpStatus.CONFLICT);
		}
		
		if(!this.quantityRestrictionService.uniteIsOk(restrictionQuantity.getQuantityAttributes())) {
			logger.error("L'unité de la Quantité n'est pas valide");
			return new ResponseEntity(new CustomError("L'unité de la quantité n'est pas valide"), HttpStatus.CONFLICT);

		}
		
		//Persistance de l'objet Quantity qui correspond a l'entite Quantity
		this.quantityRestrictionService.addQuantity(restrictionQuantity.getQuantityAttributes());
		
		logger.info("Création du groupe de restriction de quantity avec l'id : {}",restrictionQuantity.getId());
		RestrictionQuantity CurrentquantityRestrictionGroupe = this.quantityRestrictionService.createQuantityRestrictionGroupe(restrictionQuantity);		
		
		return new ResponseEntity(CurrentquantityRestrictionGroupe,HttpStatus.CREATED);
			
	}
	
	// --------------------------------------------------- Get RestrictionQuantityGroupe -------------------------------------------
	@RequestMapping(value="/{QuantityRestrictiontID}/get-quantityrestrictiongroupe", method=RequestMethod.GET)
	public ResponseEntity<RestrictionQuantity> getQuantityRestrictionGroupe( @PathVariable("quantityRestrictionID") String quantityRestrictionId){
	    Optional<RestrictionQuantity> restrictionQuantity = this.quantityRestrictionService.getQuantityRestrictionGroupe(quantityRestrictionId);
		if(restrictionQuantity.isPresent()) {
			logger.error("Récupération du groupe de restriction de Quantity ",quantityRestrictionId);
			return new ResponseEntity(restrictionQuantity.get(),HttpStatus.ACCEPTED);
		}
		logger.error("Le groupe de restrictionde Quantity {} est introuvable",quantityRestrictionId);
		return new ResponseEntity(new CustomError("le groupe de restriction de quantity avec cet ID "+quantityRestrictionId+" n'existe pas !"), HttpStatus.NOT_FOUND);

	}
	
	// --------------------------------------------------- update a RestrictionQuantityGroupe -------------------------------------------
	@RequestMapping(value="/{QuantityRestrictiontID}/update-quantityrestrictiongroupe", method=RequestMethod.PUT)
	public ResponseEntity<RestrictionQuantity> updateQuantityRestrictionGroupe(@RequestBody RestrictionQuantity restrictionQuantity, @PathVariable("QuantityRestrictiontID") String quantityRestrictiontID){
		
		RestrictionQuantity currentRestrictionQuantity = new RestrictionQuantity();
		currentRestrictionQuantity.setId(quantityRestrictiontID);
		
		if(!restrictionQuantity.getId().equals(quantityRestrictiontID)) {
			 logger.error("Un conflit dans l'id {} (Body/URL) ",restrictionQuantity.getId());
			 return new ResponseEntity(new CustomError("Un conflit dans l'id "+restrictionQuantity.getId()+" (Body/URL)"), HttpStatus.CONFLICT);
		}
		
		if(!this.quantityRestrictionService.existQuantityRestrictionGR(currentRestrictionQuantity)) {
			logger.error("Ce groupe de restriction {} n'existe pas, faudrait le créer d'abord !",currentRestrictionQuantity.getId());
			return new ResponseEntity(new CustomError("Ce groupe de restriction "+currentRestrictionQuantity.getId()+" n'existe pas, faudrait le créer d'abord !"), HttpStatus.NOT_FOUND);
		}
		
		if(restrictionQuantity.getQuantityAttributes().getValue().equals(null) ||(restrictionQuantity.getQuantityAttributes().getUnite().isEmpty())){
			logger.error("Veuillez bien renseigne les valeurs value/unite");
			return new ResponseEntity(new CustomError("Veuillez bien renseigne les valeurs value/unité"), HttpStatus.CONFLICT);
		}
		
		if(!this.quantityRestrictionService.uniteIsOk(restrictionQuantity.getQuantityAttributes())) {
			logger.error("L'unité de la Quantité n'est pas valide");
			return new ResponseEntity(new CustomError("L'unité de la quantité n'est pas valide"), HttpStatus.CONFLICT);

		}
		
		//Persistance de l'objet Quantity qui correspond a l'entite Quantity
		this.quantityRestrictionService.addQuantity(restrictionQuantity.getQuantityAttributes());
		
		logger.info("Modification du groupe de restriction de quantity avec l'id : {}",restrictionQuantity.getId());
		currentRestrictionQuantity.setLibelle(restrictionQuantity.getLibelle());
		currentRestrictionQuantity.setQuantityAttributes(restrictionQuantity.getQuantityAttributes());
		RestrictionQuantity CurrentquantityRestrictionGroupe = this.quantityRestrictionService.updateQuantityRestrictionGroupe(currentRestrictionQuantity);		
		
		return new ResponseEntity(CurrentquantityRestrictionGroupe,HttpStatus.CREATED);
		
	}





}
