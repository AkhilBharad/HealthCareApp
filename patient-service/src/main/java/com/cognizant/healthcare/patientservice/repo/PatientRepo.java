package com.cognizant.healthcare.patientservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.healthcare.patientservice.entity.PatientEntity;

@Repository
public interface PatientRepo extends JpaRepository<PatientEntity, Integer>{

}
