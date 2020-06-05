package com.cognizant.healthcare.patientservice.exception;

public class PatientNotFoundException extends RuntimeException {

	public PatientNotFoundException(String exception) {
		super(exception);
	}

	public PatientNotFoundException() {
		super("Patient with the requested id is not found");
	}

}
