package com.example.demo.services;

import java.util.Optional;

import com.example.demo.models.Quantity;
import com.example.demo.models.RestrictionAge;
import com.example.demo.models.RestrictionQuantity;

public interface RestrictionQuantityService {

	Optional<RestrictionQuantity> getQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity);
	RestrictionQuantity createQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity);
	Optional<RestrictionQuantity> updateQuantityRestrictionGroupe(RestrictionQuantity restrictionQuantity);
	boolean existQuantityRestrictionGR(RestrictionQuantity restrictionQuantity);
	boolean uniteIsOk(Quantity quantityAttributes);
	void addQuantity(Quantity quantityAttributes);

}
