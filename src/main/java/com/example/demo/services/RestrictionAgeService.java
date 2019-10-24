package com.example.demo.services;

import java.util.Optional;

import com.example.demo.models.Age;
import com.example.demo.models.RestrictionAge;

public interface RestrictionAgeService {

	boolean existAgeRestriction(RestrictionAge restrictionAge);
	boolean unitIsOk(Age ageAttribute);
	Optional<RestrictionAge> getAgeRestrictionGroupe(RestrictionAge restrictionAge);
	RestrictionAge add(RestrictionAge restrictionAge);
	void addAge(Age ageAttribute);

}
