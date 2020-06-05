package com.cognizant.healthcare.physicianservice.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.healthcare.physicianservice.entity.PhysicianEntity;
import com.cognizant.healthcare.physicianservice.exception.PhysicianNotFoundException;
import com.cognizant.healthcare.physicianservice.repo.PhysicianRepo;
import com.cognizant.healthcare.physicianservice.service.PhysicianService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PhysicianServiceImpl implements PhysicianService {

	@Autowired
	PhysicianRepo physicianRepo;

	@Override
	public List<PhysicianEntity> getAllPhysicians() throws PhysicianNotFoundException {
		List<PhysicianEntity> physiciansList = physicianRepo.findAll();
		if (!physiciansList.isEmpty())
			return physiciansList;
		else
			throw new PhysicianNotFoundException();
	}

	@Override
	public PhysicianEntity getPhysicianById(int id) throws PhysicianNotFoundException {
		PhysicianEntity physician;
		try {
			Optional<PhysicianEntity> physicianOptional = physicianRepo.findById(id);
			physician = physicianOptional.get();
		} catch (NoSuchElementException e) {
			log.error("No physician found.");
			throw new PhysicianNotFoundException();
		}
		return physician;
	}

	@Override
	public PhysicianEntity addNewPhysician(PhysicianEntity physicianEntity) throws IllegalArgumentException {
		try {
			return physicianRepo.save(physicianEntity);
		} catch (IllegalArgumentException e) {
			log.error("Illegal arguments in insert.");
			throw e;
		}
	}

	@Override
	public PhysicianEntity updatePhysician(PhysicianEntity physicianEntity) throws IllegalArgumentException {
		try {
			return physicianRepo.save(physicianEntity);
		} catch (IllegalArgumentException e) {
			log.error("Illegal arguments in update.");
			throw e;
		}
	}

	@Override
	public void deletePhysicianById(int id) throws IllegalArgumentException {
		physicianRepo.deleteById(id);
	}

	// For PowerMock testing demo
	public static String getSaticValue() {
		return "Hardcoded Message";
	}

	private String privateMethod() {
		return "Private data";
	}

}
