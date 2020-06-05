package com.cognizant.healthcare.healthcaredetailsservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhysicianEntity {
	private int id;
	private String name;
	private String specialization;
}
