package com.example.demo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.models.Category;

public class CategoryTest {

	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Test
	public void checkCategoryMapping() {
		
		CategoryDTO categoryDto = new CategoryDTO();
		categoryDto.setLibelle("Bio");
		categoryDto.setCategoryId("123-BIO");
		
		Category category = modelMapper.map(categoryDto, Category.class);
		assertEquals(categoryDto.getLibelle(), category.getLibelle());
		assertEquals(categoryDto.getCategoryId(), category.getCategoryId());
		
	}
	

}
