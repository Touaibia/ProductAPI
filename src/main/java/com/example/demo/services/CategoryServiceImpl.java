package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo categoryRepo;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepo CategoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@Override
	public boolean existCategory(Category category) {
		if(this.categoryRepo.findByCategoryId(category.getCategoryId()).isPresent())
			return true;
		return false;
	}


	@Override
	public boolean checkProductsChildrensValidity(boolean productsChildrentValidity, String productsChildrentError,
			ArrayList<Product> listProduct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkCategoriesChildrensValidity(boolean categoriesChildrentValidity,
			String categoriesChildrentError, ArrayList<Category> categoryFils) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean parentCategoryValid(Category categoryParent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsValidDates(boolean datesValidity, String datesError, Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category createCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

}
