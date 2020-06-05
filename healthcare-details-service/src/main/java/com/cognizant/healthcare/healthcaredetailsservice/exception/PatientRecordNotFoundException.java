package com.cognizant.healthcare.healthcaredetailsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientRecordNotFoundException extends RuntimeException{
	
	public PatientRecordNotFoundException(String exception) {
		super(exception);
	}

	public PatientRecordNotFoundException() {
		super("Records for patient with the requested id is not found");
	}

	public PatientRecordNotFoundException(int patientId) {
		super("Records for patient with the id " + patientId + " is not found");
	}

}
