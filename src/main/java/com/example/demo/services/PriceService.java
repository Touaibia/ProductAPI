package com.example.demo.services;

import java.util.Optional;

import com.example.demo.models.Price;

public interface PriceService {

	Price getPrice(Price prix);
	Price addPrice(Price prix);
	boolean existPrice(Price prix);
	boolean checkUnit(String unit);
	String getUnits();
	
}
