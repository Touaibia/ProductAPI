package com.example.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.standard.expression.Each;

import com.example.demo.ProductApiApplication;
import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.util.CustomError;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final String YEAR = "YEAR";
	private static final String MONTH = "MONTH";
	private static final String DAY = "DAY";
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate currentDate;
	public static final String Error ="ERROR";
	public static final String Validity = "VALIDITY";
	
	public static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	private DateValidator dateValidator;
	HashMap<String, String> YearMonthDayDateMap = new HashMap<String, String>();
	
	
	public CategoryServiceImpl() {
		
	}

	

	@Override
	public boolean existCategory(Category category) {
		Optional<Category> categoryToFind = this.categoryRepo.findByCategoryId(category.getCategoryId());
		if(categoryToFind.isPresent())
			return true;
		return false;
	}

	@Override
	public CustomError checkCategoriesChildrensValidity(CustomError checkCategoriesValidity, ArrayList<Category> categoryFils,Category categoryTocreate) {
		for (Category category : categoryFils) {
		    Optional<Category> searchedCategory = categoryRepo.findByCategoryId(category.getCategoryId());
		    //Check the existance of the category children
		    if(!searchedCategory.isPresent()) {
		    	checkCategoriesValidity.setError(false);
				checkCategoriesValidity.setErrorMessage("No children category with this ID : "+category.getCategoryId());
				return checkCategoriesValidity;
		    }else {
		    	//Check the cycle existance between the category and it's children
		    	if(categoryTocreate.getCategoryId().equals(category.getCategoryId())){
		    		checkCategoriesValidity.setError(false);
					checkCategoriesValidity.setErrorMessage("The category with this ID : "+categoryTocreate.getCategoryId()+" can't be children of itself");
					return checkCategoriesValidity;
		    	}else {
		    		//Check tif the category children don't have already a parent
		    		if(category.getCategoryParent() != null) {
		    			checkCategoriesValidity.setError(false);
						checkCategoriesValidity.setErrorMessage("The children category with this ID : "+category.getCategoryId()+" already have a category parent with this ID : "+category.getCategoryParent().getCategoryId());
						return checkCategoriesValidity;
		    		}else {
		    			if(categoryTocreate.getCategoryParent() != null && !searchedCategory.get().getCategoryFils().isEmpty()) {
		    				checkCategoriesValidity = checkCycleExistance(categoryTocreate,searchedCategory.get(),checkCategoriesValidity);
			    		    if(!checkCategoriesValidity.isError())
			    		    	return checkCategoriesValidity;
		    			}
		    			
		    		}
		    		
		    	}
		    }
		}
		return checkCategoriesValidity;
	}
	
	
	public CustomError checkCycleExistance(Category categoryToCreate, Category categoryChildren,CustomError checkCategoriesValidity) {
		for(Category cat : categoryChildren.getCategoryFils()) {
			if(cat.getCategoryId().equals(categoryToCreate.getCategoryId())) {
				checkCategoriesValidity.setError(false);
				checkCategoriesValidity.setErrorMessage("Ther's a cycle between the category with ID : "+categoryToCreate.getCategoryId()+" and children with ID : "+categoryChildren.getCategoryId());
				return checkCategoriesValidity;
			}else if(!categoryChildren.getCategoryFils().isEmpty()) {
				  checkCycleExistance(categoryToCreate,cat,checkCategoriesValidity);
			}
			   
		}
		return checkCategoriesValidity;
	}
	
	@Override
	public CustomError checkProductsChildrensValidity(CustomError checkChildrensValidity,
			ArrayList<Product> listProduct) {
		
		 
		
		return checkChildrensValidity;
	}



	@Override
	public boolean parentCategoryValid(Category categoryParent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomError IsValidDates(CustomError error,String dateDebut, String dateFin) {
		// TODO Auto-generated method stub
		 
		if(dateDebut== null) {
			error.setError(false);
			error.setErrorMessage("The Start date can't be null");			
			return error;
		}
		if(dateFin== null) {
			error.setError(false);
			error.setErrorMessage(" The end date can't be null");			
			return error;
		}
		DateValidatorUsingDateFormat dateFormat = new DateValidatorUsingDateFormat();
		if(!dateFormat.isValid(dateDebut)) {
			error.setError(false);
			error.setErrorMessage(" The Start date must have the following Format : "+dateFormat.getFormat());			
			return error;
		}
		if(!dateFormat.isValid(dateFin)) {
			error.setError(false);
			error.setErrorMessage(" The End date must have the following Format : "+dateFormat.getFormat());			
			return error;
		}
		
		 this.startDate = LocalDate.parse(dateDebut);
		 this.endDate = LocalDate.parse(dateFin);
		 this.currentDate = LocalDateTime.now().toLocalDate();
		 
		 if(!this.startDate.isAfter(this.currentDate) || !this.endDate.isAfter(this.currentDate)) {
			error.setError(false);
			error.setErrorMessage(" The Start/End dates must be after the current date : "+this.currentDate);			
			return error;
		 }
		 
		 if(!this.endDate.isAfter(startDate)) {
			 error.setError(false);
			 error.setErrorMessage("The End date must have to exceed the Startdate of at least 1 day ");			
			 return error;
		 }
		 
		 error.setError(true);
		return error;
	}

	@Override
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}



	




}
