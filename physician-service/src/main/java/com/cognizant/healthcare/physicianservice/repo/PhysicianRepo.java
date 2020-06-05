package com.cognizant.healthcare.physicianservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.healthcare.physicianservice.entity.PhysicianEntity;

@Repository
public interface PhysicianRepo extends JpaRepository<PhysicianEntity, Integer> {

}
