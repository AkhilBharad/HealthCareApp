package com.cognizant.healthcare.patientservice.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.healthcare.patientservice.entity.PatientEntity;
import com.cognizant.healthcare.patientservice.exception.PatientNotFoundException;
import com.cognizant.healthcare.patientservice.repo.PatientRepo;
import com.cognizant.healthcare.patientservice.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/patient")
@Slf4j
public class PatientController{
	@Autowired
	PatientRepo patientRepo;
	@Autowired
	PatientService patientService;

	@GetMapping("/getall")
	public ResponseEntity<?> getAllPatients() {
		try {
			List<PatientEntity> list = patientService.getAllPatients();
			return new ResponseEntity<List<PatientEntity>>(list, HttpStatus.OK);
		} catch(PatientNotFoundException e) {
			throw new PatientNotFoundException("No Patients found");
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable int id) {
		log.info("Request recieved for: " + id);
		try {
			PatientEntity patient = patientService.getPatientById(id);
			return new ResponseEntity<PatientEntity>(patient, HttpStatus.OK);
		} catch (PatientNotFoundException e) {
			throw new PatientNotFoundException("Patient with id "+ id + " not found.");
		}
	}

	@PostMapping("/add")
	public ResponseEntity<PatientEntity> addNewPatient(@RequestBody PatientEntity patient) {
		PatientEntity newPatient = patientService.addNewPatient(patient);
		return new ResponseEntity<PatientEntity>(newPatient, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable int id) {
		patientService.deletePatientById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody PatientEntity patient) {
		PatientEntity retPatient = patientService.updatePatient(patient);
		return new ResponseEntity<PatientEntity>(retPatient, HttpStatus.OK);
	}
}
