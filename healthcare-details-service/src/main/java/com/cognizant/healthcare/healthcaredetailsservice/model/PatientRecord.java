package com.cognizant.healthcare.healthcaredetailsservice.model;

import com.cognizant.healthcare.healthcaredetailsservice.entity.PatientEntity;
import com.cognizant.healthcare.healthcaredetailsservice.entity.PhysicianEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRecord {

	private int patientId;
	private String patientName;
	private int patientAge;
	private String patientGender;
	private String diseaseName;
	private int physicianId;
	private String physicianName;
	private String physicianSpecialization;
	
	public PatientRecord(PatientEntity patient, PhysicianEntity physician) {
		this.patientId = patient.getId();
		this.patientName = patient.getName();
		this.patientAge = patient.getAge();
		this.patientGender = patient.getGender();
		this.diseaseName = patient.getDiseaseName();
		this.physicianId = physician.getId();
		this.physicianName = physician.getName();
		this.physicianSpecialization = physician.getSpecialization();
	}
}
