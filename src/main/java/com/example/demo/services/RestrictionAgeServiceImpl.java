package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Age;
import com.example.demo.models.RestrictionAge;
import com.example.demo.repository.AgeRepo;
import com.example.demo.repository.RestrictionAgeRepo;

@Service
public class RestrictionAgeServiceImpl implements RestrictionAgeService {

	private RestrictionAgeRepo restrictionAgeRepo;
	private AgeRepo ageRepo;
	
	
	private ArrayList<String> ageUnits = new ArrayList<>();
	private final String JOURS = "JOURS";
	private final String MOIS = "MOIS";
	private final String AN = "AN";
	private final String ANS = "ANS";

	
	@Autowired
	public RestrictionAgeServiceImpl(RestrictionAgeRepo restrictionAgeRepo, AgeRepo ageRepo) {
		this.restrictionAgeRepo = restrictionAgeRepo;
		this.ageRepo = ageRepo;
		ageUnits.add(JOURS);
		ageUnits.add(MOIS);
		ageUnits.add(AN);
		ageUnits.add(ANS);	
	}

	@Override
	public boolean existAgeRestriction(RestrictionAge restrictionAge) {
		Optional<RestrictionAge> restrictionGroupe = this.restrictionAgeRepo.findById(restrictionAge.getId());
		if(!restrictionGroupe.isPresent())
			return false;
		return true;
	}

	@Override
	public boolean unitIsOk(Age ageAttribute) {
		if(this.ageUnits.contains(ageAttribute.getUnite().toUpperCase()))
			return true;
		return false;
	}

	public Optional<RestrictionAge> getAgeRestrictionGroupe(RestrictionAge restrictionAgeGroupe) {
		Optional<RestrictionAge> restrictionAge = this.restrictionAgeRepo.findById(restrictionAgeGroupe.getId());
		return restrictionAge;
	}
	
	
	@Override
	public RestrictionAge add(RestrictionAge restrictionAge) {
		return this.restrictionAgeRepo.save(restrictionAge);
	}

	@Override
	public void addAge(Age ageAttribute) {
		Age SavedAge= this.ageRepo.save(ageAttribute);
		
	}

	

}
