package com.cognizant.healthcare.healthcaredetailsservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.healthcare.healthcaredetailsservice.HealthcareDetailsServiceApplication;
import com.cognizant.healthcare.healthcaredetailsservice.exception.PatientRecordNotFoundException;
import com.cognizant.healthcare.healthcaredetailsservice.model.PatientRecord;
import com.cognizant.healthcare.healthcaredetailsservice.service.HealthCareDetailService;

@SpringBootTest(classes = HealthcareDetailsServiceApplication.class)
public class HealthCareDetailsControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private HealthCareDetailService healthCareDetailService;
	
	@InjectMocks
	private HealthCareDetailsController healthCareDetailsController;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(healthCareDetailsController).build();
	}
	
	@Test
	public void testGetPatientRecordSuccess() throws Exception {
		
		Mockito.when(healthCareDetailService.getPatientRecord(1))
			.thenReturn(new PatientRecord(1, "ABC", 22, "male", "xyz", 4, "LMNO", "mlo"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/healthcare/get-record/1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.patientId").value("1"));
		
	}
	
	@Test
	public void testGetPatientRecordFailure() throws Exception {
		
		Mockito.when(healthCareDetailService.getPatientRecord(1)).thenThrow(new PatientRecordNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/healthcare/get-record/1"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}

}
