package com.example.demo.services;

import java.util.Optional;

import com.example.demo.models.Quantity;
import com.example.demo.models.RestrictionAge;
import com.example.demo.models.RestrictionQuantity;

public interface RestrictionQuantityService {

	RestrictionQuantity createQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity);
	RestrictionQuantity updateQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity);
	boolean existQuantityRestrictionGR(RestrictionQuantity restrictionQuantity);
	boolean uniteIsOk(Quantity quantityAttributes);
	void addQuantity(Quantity quantityAttributes);
	Optional<RestrictionQuantity> getQuantityRestrictionGroupe(String restrictionQuantityID);

}
