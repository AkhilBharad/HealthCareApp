package com.cognizant.healthcare.patientservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.healthcare.patientservice.PatientServiceApplication;
import com.cognizant.healthcare.patientservice.entity.PatientEntity;
import com.cognizant.healthcare.patientservice.exception.PatientNotFoundException;
import com.cognizant.healthcare.patientservice.service.PatientService;

@SpringBootTest(classes = PatientServiceApplication.class)
class PatientControllerTest {
	
	@InjectMocks
	private PatientController controller;
	
	@Mock
	private PatientService service;
	
	@Test
	public void getAllPatientsTest() throws InterruptedException {
		List<PatientEntity> list = new ArrayList<PatientEntity>();

		PatientEntity patient1 = new PatientEntity(1, "Raavan", 50, "male", "Anger", 4);
		PatientEntity patient2 = new PatientEntity(3, "Shakuni mama", 60, "male", "greed", 5);

		list.add(patient1);
		list.add(patient2);

		when(service.getAllPatients()).thenReturn(list);
		
		List<PatientEntity> patientList = (List<PatientEntity>) controller.getAllPatients().getBody();

		assertEquals(2, patientList.size());
		verify(service, times(1)).getAllPatients();
	}
	
	@Test
	public void getAllPatientsTestForException() throws InterruptedException {
			
		Exception exception = assertThrows(PatientNotFoundException.class, () -> {
			when(service.getAllPatients()).thenThrow(PatientNotFoundException.class);
			controller.getAllPatients();
		});

		assertNotNull(exception);
		verify(service, times(1)).getAllPatients();
	}
	
	@Test
	public void getPatientByIdTest() {
		when(service.getPatientById(1)).thenReturn(new PatientEntity(1, "Lokesh", 34, "male", "Asthama", 3));

		PatientEntity patient = (PatientEntity) controller.getPatientById(1).getBody();

		assertEquals("Lokesh", patient.getName());
		verify(service, times(1)).getPatientById(1);
	}

	@Test
	public void getPatientByIdTestForException() {
		Exception exception = assertThrows(PatientNotFoundException.class, () -> {
			when(service.getPatientById(1)).thenThrow(PatientNotFoundException.class);
			controller.getPatientById(1);
		});

		assertNotNull(exception);
		verify(service, times(1)).getPatientById(1);
	}
	
	@Test
	public void addNewPatientTest() {
		when(service.addNewPatient(Mockito.any(PatientEntity.class))).thenAnswer(i -> i.getArgument(0));
		
		PatientEntity patient = new PatientEntity(1, "Raavan", 50, "male", "Anger", 4);
		
		assertTrue(patient.equals(controller.addNewPatient(patient).getBody()));
		verify(service, times(1)).addNewPatient(Mockito.any(PatientEntity.class));
	}
	
	@Test
	public void addNewPatientTestForException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			when(service.addNewPatient(Mockito.any(PatientEntity.class))).thenThrow(IllegalArgumentException.class);
			PatientEntity patient = new PatientEntity(1, "Raavan", 50, "male", "Anger", 4);
			controller.addNewPatient(patient);
		});

		assertNotNull(exception);
		verify(service, times(1)).addNewPatient(Mockito.any(PatientEntity.class));
	}
}
