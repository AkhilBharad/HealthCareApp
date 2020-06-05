package com.cognizant.healthcare.physicianservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.healthcare.physicianservice.entity.PhysicianEntity;
import com.cognizant.healthcare.physicianservice.exception.PhysicianNotFoundException;
import com.cognizant.healthcare.physicianservice.service.PhysicianService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/physician")
@Slf4j
public class PhysicianController{
	
	@Autowired
	PhysicianService physicianService;

	@GetMapping("/getall")
	public ResponseEntity<?> getAllPhysicians() {
		try {
			List<PhysicianEntity> list = physicianService.getAllPhysicians();
			return new ResponseEntity<List<PhysicianEntity>>(list, HttpStatus.OK);
		} catch(PhysicianNotFoundException e) {
			throw new PhysicianNotFoundException("No Physicians found");
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getPhysicianById(@PathVariable int id) {
		log.info("Get request recieved for {}" , id);
		try {
			PhysicianEntity physician = physicianService.getPhysicianById(id);
			return new ResponseEntity<PhysicianEntity>(physician, HttpStatus.OK);
		} catch (PhysicianNotFoundException e) {
			throw new PhysicianNotFoundException("Physician with id "+ id + " not found.");
		}
	}

	@PostMapping("/add")
	public ResponseEntity<PhysicianEntity> addNewPhysician(@RequestBody PhysicianEntity physician) {
		PhysicianEntity newPhysician = physicianService.addNewPhysician(physician);
		log.info("Physician created with id {}" , newPhysician.getId());
		return new ResponseEntity<PhysicianEntity>(newPhysician, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePhysician(@PathVariable int id) {
		log.info("Delete request recieved for {}" , id);
		physicianService.deletePhysicianById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody PhysicianEntity physician) {
		log.info("Update request recieved for {}" , physician.getId());
		PhysicianEntity retPhysician = physicianService.updatePhysician(physician);
		return new ResponseEntity<PhysicianEntity>(retPhysician, HttpStatus.OK);
	}
}
