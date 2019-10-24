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

import com.example.demo.models.Price;
import com.example.demo.models.RestrictionAge;
import com.example.demo.services.*;
import com.example.demo.util.CustomError;


@RestController
@RequestMapping("/agerestriction-api")
public class AgeRestrictionGrController {

	@Autowired
	private RestrictionAgeService ageRestrictionService;
	
    public static final Logger logger = LoggerFactory.getLogger(AgeRestrictionGrController.class);
    
    
	// --------------------------------------------------- Create a RestrictionAgeGroupe -------------------------------------------
	@RequestMapping(value="/{AgeRestrictiontID}/create-agerestrictiongroupe", method=RequestMethod.POST)
    public ResponseEntity<RestrictionAge> createAgeRestrictionGroupe(@RequestBody  RestrictionAge restrictionAge,@PathVariable("AgeRestrictiontID") String ageRestricitionID){
		
		if(ageRestricitionID.isEmpty() || restrictionAge.getId().isEmpty() || !restrictionAge.getId().equals(ageRestricitionID)) {
			 logger.error("Un conflit dans l'id {} (Body/URL) ",restrictionAge.getId());
			 return new ResponseEntity(new CustomError("Un conflit dans l'id "+restrictionAge.getId()+" (Body/URL)"), HttpStatus.CONFLICT);
		}
		if(this.ageRestrictionService.existAgeRestriction(restrictionAge)) {
			logger.error("Ce groupe de restriction {} existe déja",restrictionAge.getId());
			return new ResponseEntity(new CustomError("Ce groupe de restriction "+restrictionAge.getId()+" existe déja"), HttpStatus.CONFLICT);
		}
		
		if(restrictionAge.getAgeAttribute().getAge().equals(null) ||(restrictionAge.getAgeAttribute().getUnite().isEmpty())){
			logger.error("Veuillez bien renseigne les valeurs age/unité");
			return new ResponseEntity(new CustomError("Veuillez bien renseigne les valeurs age/unité"), HttpStatus.CONFLICT);
		}
		
		if(!this.ageRestrictionService.unitIsOk(restrictionAge.getAgeAttribute())) {
			logger.error("L'unité d'age n'est pas valide");
			return new ResponseEntity(new CustomError("L'unité d'age n'est pas valide"), HttpStatus.CONFLICT);

		}
		
		//Persistance de l'objet Age qui correspond a l'entite Age
		this.ageRestrictionService.addAge(restrictionAge.getAgeAttribute());
		
		RestrictionAge CurrentageRestrictionGroupe = this.ageRestrictionService.add(restrictionAge);		
		return new ResponseEntity(CurrentageRestrictionGroupe,HttpStatus.CREATED);
		
	}
	
	// --------------------------------------------- Get GroupeRestrictionAge -----------------------------------------------------------
	
	@RequestMapping(value="/get-age/{ageRestrictionID}", method= RequestMethod.GET)
	public ResponseEntity<RestrictionAge> getAgeRestrictionGroupe( @PathVariable("ageRestrictionID") String ageRestrictionId){
	    RestrictionAge CurrentRestrictionAge = new RestrictionAge();
		if(ageRestrictionId.isEmpty()) {
			logger.error("L'Id du groupe de Restriction d'age doit etre différent de Null");
			return new ResponseEntity(new CustomError("L'Id du groupe de Restrictiond'age doit etre différent de Null"),HttpStatus.BAD_REQUEST);
		}
		
		CurrentRestrictionAge.setId(ageRestrictionId);
		if(!this.ageRestrictionService.existAgeRestriction(CurrentRestrictionAge)) {
			logger.error("le groupe de restriction d'age avec cet ID "+CurrentRestrictionAge.getId()+" n'existe pas !");
			return new ResponseEntity(new CustomError("le groupe de restriction d'age avec cet ID "+CurrentRestrictionAge.getId()+" n'existe pas !"), HttpStatus.NOT_FOUND);
		}
		else
			return new ResponseEntity(this.ageRestrictionService.getAgeRestrictionGroupe(CurrentRestrictionAge), HttpStatus.ACCEPTED);
		
	}
	
	// --------------------------------------------------- update a RestrictionAgeGroupe -------------------------------------------
	@RequestMapping(value="/{ageRestrictionID}/update-restrictionAgeGroupe", method= RequestMethod.PUT)
	public ResponseEntity<RestrictionAge> updateRestrictionGroupe(@RequestBody RestrictionAge restrictionAge,@PathVariable("ageRestrictionID") String ageRestrictionID){
		if(!restrictionAge.getId().equals(ageRestrictionID)) {
			 logger.error("Un conflit dans l'id {} (Body/URL) ",restrictionAge.getId());
			 return new ResponseEntity(new CustomError("Un conflit dans l'id "+restrictionAge.getId()+" (Body/URL)"), HttpStatus.CONFLICT);
		}
		if(!this.ageRestrictionService.existAgeRestriction(restrictionAge)) {
			logger.error("Ce groupe de restriction {} n'existe pas, faudrait le créer d'abord !",restrictionAge.getId());
			return new ResponseEntity(new CustomError("Ce groupe de restriction "+restrictionAge.getId()+" n'existe pas, faudrait le créer d'abord"), HttpStatus.CONFLICT);
		}
		if(restrictionAge.getAgeAttribute().getAge().equals(null) ||(restrictionAge.getAgeAttribute().getUnite().isEmpty())){
			logger.error("Veuillez bien renseigne les valeurs age/unité");
			return new ResponseEntity(new CustomError("Veuillez bien renseigne les valeurs age/unité"), HttpStatus.CONFLICT);
		}
		
		if(!this.ageRestrictionService.unitIsOk(restrictionAge.getAgeAttribute())) {
			logger.error("L'unité d'age n'est pas valide");
			return new ResponseEntity(new CustomError("L'unité d'age n'est pas valide"), HttpStatus.CONFLICT);

		}
		
		//Persistance de l'objet Age qui correspond a l'entite Age
		this.ageRestrictionService.addAge(restrictionAge.getAgeAttribute());			
		RestrictionAge CurrentageRestrictionGroupe = this.ageRestrictionService.add(restrictionAge);		
		return new ResponseEntity(CurrentageRestrictionGroupe,HttpStatus.CREATED);
		
	}


}


	
