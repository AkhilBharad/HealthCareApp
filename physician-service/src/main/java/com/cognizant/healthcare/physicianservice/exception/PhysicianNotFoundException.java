package com.cognizant.healthcare.physicianservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PhysicianNotFoundException extends RuntimeException{
	
	public PhysicianNotFoundException(String exception) {
		super(exception);
	}

	public PhysicianNotFoundException() {
		super("Physician with the requested id is not found");
	}

}
