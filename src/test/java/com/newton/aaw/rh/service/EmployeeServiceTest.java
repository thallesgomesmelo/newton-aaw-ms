package com.newton.aaw.rh.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.newton.aaw.rh.domain.entity.Employee;
import com.newton.aaw.rh.domain.repository.EmployeeRepository;
import com.newton.aaw.rh.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	private EmployeeService unit;
	
	@BeforeEach
	public void setup() {
		unit = new EmployeeService(employeeRepository);
	}
	
	@Test
	void test_getById_withEmployee_shouldReturnObj() {
		//Given:
		var employee = new Employee();
		employee.setId("employee1");
		
		//Mock definitions: 
		Mockito.when(employeeRepository.findById("employee1")).thenReturn(Optional.of(employee));
		
		//Test: 
		var result = unit.get("employee1");
		
		//Assert:
		Assertions.assertEquals(result, employee);
		
		//Verify:
		Mockito.verify(employeeRepository).findById("employee1");
	}
	@Test
	void test_getById_withNoEmployee_shouldThrowException() {
		//Given:
//		var id = "employee1"
		
		//Mock definitions:
		Mockito.when(employeeRepository.findById("employee1")).thenReturn(Optional.empty());
		
		//Test: 
		try {
			unit.get("employee1");
			fail("Expected NotFoundException");
		} catch (NotFoundException ex) {
			//Assert:
			Assertions.assertEquals(ex.getMessage(), "Employee with ID employee1");
		}
		
		//Verify:
		Mockito.verify(employeeRepository).findById("employee1");
	}

}
