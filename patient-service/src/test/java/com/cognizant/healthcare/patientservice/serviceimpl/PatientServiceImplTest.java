package com.cognizant.healthcare.patientservice.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.healthcare.patientservice.PatientServiceApplication;
import com.cognizant.healthcare.patientservice.entity.PatientEntity;
import com.cognizant.healthcare.patientservice.exception.PatientNotFoundException;
import com.cognizant.healthcare.patientservice.repo.PatientRepo;
import com.cognizant.healthcare.patientservice.service.PatientService;
import com.google.common.util.concurrent.UncheckedExecutionException;

@SpringBootTest(classes = PatientServiceApplication.class)
class PatientServiceImplTest {

	@InjectMocks
	PatientService patientService = new PatientServiceImpl();
	
	@Mock
	PatientRepo patientRepo;

	@Test
	public void getAllPatientsTest() {
		List<PatientEntity> list = new ArrayList<PatientEntity>();

		PatientEntity patient1 = new PatientEntity(1, "Raavan", 50, "male", "Anger", 4);
		PatientEntity patient2 = new PatientEntity(3, "Shakuni mama", 60, "male", "greed", 5);

		list.add(patient1);
		list.add(patient2);

		when(patientRepo.findAll()).thenReturn(list);

		List<PatientEntity> patientList = patientService.getAllPatients();

		assertEquals(2, patientList.size());
		verify(patientRepo, times(1)).findAll();
	}

	@Test
	public void getAllPatientsTestForException() {
		Exception exception = assertThrows(PatientNotFoundException.class, () -> {
			when(patientRepo.findAll()).thenReturn(new ArrayList<PatientEntity>());
			patientService.getAllPatients();
		});

		assertNotNull(exception);
		verify(patientRepo, times(1)).findAll();
	}

	@Test
	public void getPatientByIdTest() {
		when(patientRepo.findById(1)).thenReturn(Optional.of(new PatientEntity(1, "Lokesh", 34, "male", "Asthama", 3)));

		PatientEntity patient = patientService.getPatientById(1);

		assertEquals("Lokesh", patient.getName());
		verify(patientRepo, times(1)).findById(1);
	}

	@Test
	public void getPatientByIdTestForException() {
		Exception exception = assertThrows(PatientNotFoundException.class, () -> {
			when(patientRepo.findById(1)).thenThrow(PatientNotFoundException.class);
			patientService.getPatientById(1);
		});

		assertNotNull(exception);
		verify(patientRepo, times(1)).findById(1);
	}

	@Test
	public void addNewPatientTest() {
		when(patientRepo.save(Mockito.any(PatientEntity.class))).thenAnswer(i -> i.getArgument(0));
		
		PatientEntity patient = new PatientEntity(1, "Raavan", 50, "male", "Anger", 4);
		
		assertTrue(patient.equals(patientService.addNewPatient(patient)));
		verify(patientRepo, times(1)).save(Mockito.any(PatientEntity.class));
	}
	
	@Test
	public void addNewPatientTestForException() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			when(patientRepo.save(Mockito.any(PatientEntity.class))).thenThrow(IllegalArgumentException.class);
			patientService.addNewPatient(new PatientEntity());
		});

		assertNotNull(exception);
		verify(patientRepo, times(1)).save(Mockito.any(PatientEntity.class));
	}

	
}
