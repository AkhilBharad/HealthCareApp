package com.cognizant.healthcare.physicianservice.serviceimpl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.healthcare.physicianservice.PhysicianServiceApplication;

@SpringBootTest(classes = PhysicianServiceApplication.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({PhysicianServiceImpl.class})
public class PhysicianServiceImplTest {

	@Test
	public void testGetSaticValue() {
		PowerMockito.mockStatic(PhysicianServiceImpl.class);
		PowerMockito.when(PhysicianServiceImpl.getSaticValue()).thenReturn("Test Value");
		
		assertEquals("Test Value", PhysicianServiceImpl.getSaticValue());
	}
	
	@Test
	public void testPrivateMethod() throws Exception {
		PhysicianServiceImpl mock = PowerMockito.spy(new PhysicianServiceImpl());
		PowerMockito.when(mock, "privateMethod").thenReturn("Test Value");
		
		assertEquals("Test Value", Whitebox.invokeMethod(mock, "privateMethod"));
	}
	
	
}
