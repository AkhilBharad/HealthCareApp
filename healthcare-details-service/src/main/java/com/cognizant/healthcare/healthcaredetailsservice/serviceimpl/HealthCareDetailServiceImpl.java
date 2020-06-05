package com.cognizant.healthcare.healthcaredetailsservice.serviceimpl;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cognizant.healthcare.healthcaredetailsservice.entity.PatientEntity;
import com.cognizant.healthcare.healthcaredetailsservice.entity.PhysicianEntity;
import com.cognizant.healthcare.healthcaredetailsservice.exception.PatientRecordNotFoundException;
import com.cognizant.healthcare.healthcaredetailsservice.model.PatientRecord;
import com.cognizant.healthcare.healthcaredetailsservice.service.HealthCareDetailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HealthCareDetailServiceImpl implements HealthCareDetailService {

	@Value("${patient-service.url}")
	String patientServiceUrl;

	@Value("${physician-service.url}")
	String physicianServiceUrl;

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackPatientRecord", ignoreExceptions = { PatientRecordNotFoundException.class })
	@Override
	public PatientRecord getPatientRecord(int patientId) {

		Map<String, String> patientParams = new HashMap<>();
		patientParams.put("id", Integer.toString(patientId));
		try {
			log.info("calling url: " + patientServiceUrl + "get/" + patientId);
			ResponseEntity<PatientEntity> patientResponse = restTemplate.getForEntity(patientServiceUrl + "get/{id}",
					PatientEntity.class, patientParams);
			PatientEntity patient = patientResponse.getBody();
			log.debug("The response: {}",patient);
			Map<String, String> physicianParams = new HashMap<>();
			physicianParams.put("id", Integer.toString(patient.getPhysicianId()));
			log.info("calling url: " + physicianServiceUrl + "get/" + patient.getPhysicianId());
			ResponseEntity<PhysicianEntity> physicianResponse = restTemplate.getForEntity(physicianServiceUrl + "get/{id}",
					PhysicianEntity.class, physicianParams);
			PhysicianEntity physician = physicianResponse.getBody();
			log.debug("The response: {}",physician);
			PatientRecord record = new PatientRecord(patient, physician);
			log.info("The record to return --> {}",record);
			return record;
		} catch (RestClientException e) {
			log.error("Exception : {}",e);
			throw new PatientRecordNotFoundException();
		}
	}

	public PatientRecord getFallbackPatientRecord(int patientId) {

		log.info("Fallback invoked. Some service is down or not respondin or the patient is not found");
		return new PatientRecord(0, "dafault", 0, "default", "default", 0, "default", "default");
	}

}
