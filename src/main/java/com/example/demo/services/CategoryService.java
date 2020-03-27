package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.util.CustomError;

public interface CategoryService {

	boolean existCategory(Category category);
	CustomError checkCategoriesChildrensValidity(CustomError checkCategoriesValidity,
			ArrayList<Category> categoryFils,Category category );
	boolean parentCategoryValid(Category categoryParent);
	CustomError IsValidDates(CustomError error,String dateDebut, String dateFin);
	Category createCategory(Category category);
	CustomError checkProductsChildrensValidity(CustomError checkChildrensValidity,
			ArrayList<Product> listProduct);

}
