package com.cognizant.healthcare.healthcaredetailsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.healthcare.healthcaredetailsservice.exception.PatientRecordNotFoundException;
import com.cognizant.healthcare.healthcaredetailsservice.model.PatientRecord;
import com.cognizant.healthcare.healthcaredetailsservice.service.HealthCareDetailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/healthcare/")
@Slf4j
public class HealthCareDetailsController {
	
	@Autowired
	public HealthCareDetailService service;
	
	@GetMapping("/get-record/{id}")
	public ResponseEntity<?> getPatientRecord(@PathVariable("id") int id) {
		log.info("Request recieved for {} ",id);
		PatientRecord patientRecord = null;
		try {
			patientRecord = service.getPatientRecord(id);
		} catch (PatientRecordNotFoundException e) {
			log.error("patient records not found");
			throw new PatientRecordNotFoundException(id);
		}
		return new ResponseEntity<PatientRecord>(patientRecord, HttpStatus.OK);
	}	
}
