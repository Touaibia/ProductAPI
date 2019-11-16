package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Quantity;
import com.example.demo.models.RestrictionQuantity;
import com.example.demo.repository.RestrictionQuantityRepo;

@Service
public class RestrictionQuantityServiceImpl implements RestrictionQuantityService {
	
	@Autowired
	public RestrictionQuantityRepo quantityRestrectionRepo;

	public RestrictionQuantityServiceImpl() {
		
	}

	@Override
	public Optional<RestrictionQuantity> getQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestrictionQuantity createQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity) {
		
		return null;
	}

	@Override
	public Optional<RestrictionQuantity> updateQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existQuantityRestrictionGR(RestrictionQuantity restrictionQuantity) {
		if(!restrictionQuantity.getId().isEmpty()) {
			Optional<RestrictionQuantity> quantityRescGr = quantityRestrectionRepo.findById(restrictionQuantity.getId());
			if(quantityRescGr.isPresent())
				return true;
		}
		return false;
	}

	@Override
	public boolean uniteIsOk(Quantity quantityAttributes) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addQuantity(Quantity quantityAttributes) {
		// TODO Auto-generated method stub
		
	}
	

}
