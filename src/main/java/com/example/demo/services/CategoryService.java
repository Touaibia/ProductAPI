package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;

import com.example.demo.models.Category;
import com.example.demo.models.Product;

public interface CategoryService {

	boolean existCategory(Category category);
	boolean checkProductsChildrensValidity(boolean productsChildrentValidity, String productsChildrentError, ArrayList<Product> listProduct);
	boolean checkCategoriesChildrensValidity(boolean categoriesChildrentValidity, String categoriesChildrentError,
			ArrayList<Category> categoryFils);
	boolean parentCategoryValid(Category categoryParent);
	boolean IsValidDates(boolean datesValidity, String datesError, Date dateDebut, Date dateFin);
	Category createCategory(Category category);

}
