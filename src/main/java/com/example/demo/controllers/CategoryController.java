package com.example.demo.controllers;

import java.util.HashMap;

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

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;
import com.example.demo.util.CustomError;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	
	// --------------------------------------------------- Create Category ------------------------------------------------------
	@RequestMapping(value="/{CategoryId}/create-category", method=RequestMethod.POST)
	public ResponseEntity<Category> createCategory(@RequestBody Category category, @PathVariable("CategoryId") String categoryId){
		
		if(categoryId.isEmpty() || category.getCategoryId().isEmpty() || !category.getCategoryId().equals(categoryId)) {
			 logger.error("Un conflit dans l'id {} (Body/URL) ",category.getCategoryId());
			 return new ResponseEntity(new CustomError("Un conflit dans l'id "+category.getCategoryId()+" (Body/URL)"), HttpStatus.CONFLICT);
		}
		
		if(this.categoryService.existCategory(category)) {
			logger.error("Cette Category {} existe déja",category.getCategoryId());
			return new ResponseEntity(new CustomError("Cette Category : "+category.getCategoryId()+" existe déja"), HttpStatus.CONFLICT);
		}
		
	   if(category.getLibelle().isEmpty()) {
		    logger.error("Cette Category {} doit avoir un Libellé différent de null",category.getCategoryId());
			return new ResponseEntity(new CustomError("Cette Category : "+category.getCategoryId()+" doit avoir un Libellé différent de null"), HttpStatus.CONFLICT);	
	   }
	   
	  
	   CustomError checkDatesValidity = new CustomError();	   
	   checkDatesValidity = this.categoryService.IsValidDates(checkDatesValidity,category.getDateDebut(), category.getDateFin());
	   if(!checkDatesValidity.isError()) {
		    logger.error(checkDatesValidity.getErrorMessage());
			return new ResponseEntity(new CustomError(checkDatesValidity.getErrorMessage()), HttpStatus.CONFLICT);
	   }
	   
      //en test
	   if(!category.getCategoryFils().isEmpty()) {
		   CustomError checkChildrenCategoriesValidity = new CustomError();	
		   checkChildrenCategoriesValidity = this.categoryService.checkCategoriesChildrensValidity(checkChildrenCategoriesValidity,category.getCategoryFils(),category);
		   if(!checkChildrenCategoriesValidity.isError()) {
			    logger.error(checkChildrenCategoriesValidity.getErrorMessage());
				return new ResponseEntity(new CustomError(checkChildrenCategoriesValidity.getErrorMessage()), HttpStatus.CONFLICT);
		   } 
		 
	   }
	  
	   /*  //ToDo
	   if(!category.getListProduct().isEmpty()) {
		   CustomError checkChildrensValidity = new CustomError();	 
		   checkChildrensValidity = this.categoryService.checkProductsChildrensValidity(checkChildrensValidity,category.getListProduct(), );
		   if(!checkChildrensValidity.isError()) {
			    logger.error(checkChildrensValidity.getErrorMessage());
				return new ResponseEntity(new CustomError(checkChildrensValidity.getErrorMessage()), HttpStatus.CONFLICT);
		   } 
	   }  
	   */  
	 
	   
	   /* //Todo
	   if(category.getCategoryParent() != null) {
		   if(!this.categoryService.parentCategoryValid(category.getCategoryParent())) {
			   logger.error("La Category Parent pour cette categoré {} n'est pas valide ",category.getCategoryId());
				return new ResponseEntity(new CustomError("La Category Parent pour cette categoré : "+category.getCategoryId()+" n'est pas valide"), HttpStatus.CONFLICT);
		   
		   }
	   }
	  
	      */
   
	   logger.error("Création de la categorie : {}  ",category.getCategoryId());
	  //Todo
	   Category newCategory = this.categoryService.createCategory(category);
	   
	   return new ResponseEntity(newCategory, HttpStatus.CREATED);
		}

}
