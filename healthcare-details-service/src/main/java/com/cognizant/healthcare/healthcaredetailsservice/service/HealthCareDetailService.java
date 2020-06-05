package com.cognizant.healthcare.healthcaredetailsservice.service;

import org.springframework.stereotype.Component;

import com.cognizant.healthcare.healthcaredetailsservice.model.PatientRecord;

public interface HealthCareDetailService {

	public PatientRecord getPatientRecord(int patientId);
}
