package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Quantity;
import com.example.demo.models.RestrictionQuantity;
import com.example.demo.repository.QuantityRepo;
import com.example.demo.repository.RestrictionQuantityRepo;

@Service
public class RestrictionQuantityServiceImpl implements RestrictionQuantityService {
	
	
	public RestrictionQuantityRepo quantityRestrectionRepo;
	public QuantityRepo quantityRepo;
	
	private ArrayList<String> quantityUnits = new ArrayList<>();
	private final String KG = "KG";
	private final String G = "G";
	private final String L = "L";
	

	@Autowired
	public RestrictionQuantityServiceImpl(RestrictionQuantityRepo quantityRestrectionRepo, QuantityRepo quantityRepo ) {
		this.quantityRepo = quantityRepo;
		this.quantityRestrectionRepo = quantityRestrectionRepo;
		quantityUnits.add(KG);
		quantityUnits.add(G);
		quantityUnits.add(L);		
	}

	@Override
	public Optional<RestrictionQuantity> getQuantityRestrictionGroupe(String restrictionQuantityID) {
		return  this.quantityRestrectionRepo.findById(restrictionQuantityID);
		
	}

	@Override
	public RestrictionQuantity createQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity) {
		return this.quantityRestrectionRepo.save(restrictionQuantity);
	}

	@Override
	public RestrictionQuantity updateQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existQuantityRestrictionGR(RestrictionQuantity restrictionQuantity) {
		if(!restrictionQuantity.getId().isEmpty()) {
			Optional<Optional<RestrictionQuantity>> quantityRescGr = Optional.ofNullable(quantityRestrectionRepo.findById(restrictionQuantity.getId()));
			System.out.println(" le groupe a chercher est : "+quantityRescGr);
			if(quantityRescGr.isPresent())
				return true;
		}
		return false;
	}

	@Override
	public boolean uniteIsOk(Quantity quantityAttributes) {
		if(quantityUnits.contains(quantityAttributes.getUnite().toUpperCase()))
			return true;
		return false;
	}

	public void addQuantity(Quantity quantityAttributes) {
		 Optional<Optional<Quantity>> quantityTofind = Optional.ofNullable(this.quantityRepo.findByValueAndUnite(quantityAttributes.getValue(),quantityAttributes.getUnite()));
		 System.out.println("product to save is 1 : "+quantityTofind.get());

		 if(!quantityTofind.get().isPresent()) {
			 quantityRepo.save(quantityAttributes);	
		 }
					 
	}
	

}
