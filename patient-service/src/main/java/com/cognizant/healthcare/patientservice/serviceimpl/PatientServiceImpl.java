package com.cognizant.healthcare.patientservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cognizant.healthcare.patientservice.entity.PatientEntity;
import com.cognizant.healthcare.patientservice.exception.PatientNotFoundException;
import com.cognizant.healthcare.patientservice.repo.PatientRepo;
import com.cognizant.healthcare.patientservice.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService{

	@Autowired
	PatientRepo patientRepo;

	@Override
	public List<PatientEntity> getAllPatients() throws PatientNotFoundException {
		List<PatientEntity> patientsList = patientRepo.findAll();
		if(!patientsList.isEmpty())
			return patientsList;
		else
			throw new PatientNotFoundException();
	}

	@Override
	public PatientEntity getPatientById(int id) throws PatientNotFoundException {
		PatientEntity patient;
		try {
			Optional<PatientEntity> patientOptional=patientRepo.findById(id);
			patient = patientOptional.get();
		} catch (Exception e) {
			log.error("No patient found.");
			throw new PatientNotFoundException();
		}
		return patient;
	}

	@Override
	public PatientEntity addNewPatient(PatientEntity patientEntity) throws IllegalArgumentException {
		try {
			return patientRepo.save(patientEntity);
		} catch (IllegalArgumentException e) {
			log.error("Illegal arguments in insert.");
			throw e;
		}
	}
	
	@Override
	public PatientEntity updatePatient(PatientEntity patientEntity) throws IllegalArgumentException {
		try {
			return patientRepo.save(patientEntity);
		} catch (IllegalArgumentException e) {
			log.error("Illegal arguments in update.");
			throw e;
		}
	}
	
	@Override
	public boolean deletePatientById(int id) throws IllegalArgumentException {
		patientRepo.deleteById(id);
		try {
			this.getPatientById(id);
		} catch (PatientNotFoundException e) {
			log.error("Unable to delete patient.");
			return false;
		}
		return true;
	}	
}
