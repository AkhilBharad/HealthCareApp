package com.cognizant.healthcare.healthcaredetailsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientEntity {
	private Integer id;
	private String name;
	private int age;
	private String gender;
	private String diseaseName;
	private int physicianId;
	
}
