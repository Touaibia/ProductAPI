package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Price;
import com.example.demo.repository.PriceRepo;

@Service
public class PriceServiceImpl implements PriceService{
	
	@Autowired
	private PriceRepo priceRepo;
	private ArrayList<String> Units = new ArrayList<>() ;
	
	
	public PriceServiceImpl() {	
		this.Units.add("EURO");
		this.Units.add("DOLLAR");	
	}

	public Price getPrice(Price prix) {	
		
		Optional<Price> price = this.priceRepo.findByProductID(prix.getProductID());
		if(price.isPresent())
			return price.get();
		return null;	
	}

	@Override
	public Price addPrice(Price prix) {
		return this.priceRepo.save(prix);
	}

	
	@Override
	public boolean existPrice(Price prix) {
		if(prix.getProductID().equals(null))
			return false;
		
		Optional<Price> priceFound = this.priceRepo.findByProductID(prix.getProductID());
		if(priceFound.isPresent())
			return true;
		return false;
	}

	@Override
	public boolean checkUnit(String unit) {
		if(this.Units.contains(unit.toUpperCase()))
			return true;
		return false;
	}

	@Override
	public String getUnits() {
		String ListOfUnits="";
		for(int i=0;i<Units.size();i++)
			ListOfUnits += Units.get(i)+" / ";
		return ListOfUnits;
	}

	

}
