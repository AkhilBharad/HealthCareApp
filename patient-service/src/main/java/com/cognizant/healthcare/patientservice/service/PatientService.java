package com.cognizant.healthcare.patientservice.service;

import java.util.List;

import com.cognizant.healthcare.patientservice.entity.PatientEntity;

public interface PatientService {

	public PatientEntity addNewPatient(PatientEntity patientEntity);
	
    public PatientEntity updatePatient(PatientEntity patientEntity);
	
	public boolean deletePatientById(int id);
	
    public List<PatientEntity> getAllPatients();
	
	public PatientEntity getPatientById(int id);
}
