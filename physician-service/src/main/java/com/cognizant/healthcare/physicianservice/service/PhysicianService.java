package com.cognizant.healthcare.physicianservice.service;

import java.util.List;

import com.cognizant.healthcare.physicianservice.entity.PhysicianEntity;

public interface PhysicianService {
	
public PhysicianEntity addNewPhysician(PhysicianEntity physicianEntity);
	
    public PhysicianEntity updatePhysician(PhysicianEntity physicianEntity);
	
	public void deletePhysicianById(int id);
	
    public List<PhysicianEntity> getAllPhysicians();
	
	public PhysicianEntity getPhysicianById(int id);

}
