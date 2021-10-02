package com.newton.aaw.rh.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.newton.aaw.rh.domain.entity.Employee;
import com.newton.aaw.rh.domain.enums.Gender;
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
		var id = "employee1";
		
		//Mock definitions:
		Mockito.when(employeeRepository.findById("employee1")).thenReturn(Optional.empty());
		
		//Test: 
		try {
			unit.get(id);
			fail("Expected NotFoundException");
		} catch (NotFoundException ex) {
			//Assert:
			Assertions.assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");
		}
		
		//Verify:
		Mockito.verify(employeeRepository).findById(id);
	}
	
	@Test
	void test_delete_withValidaId_shouldDeletOk() {
		//Given
		var id = "001";
		var employee = new Employee();
		
		//Mock Definitions || 
		Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
		
		//Test
		unit.delete(id);
		
		//Assert || testa o retorno mas a classe delete e "void" 
		
		//Verify
		Mockito.verify(employeeRepository).findById(id); //
		Mockito.verify(employeeRepository).deleteById(id); //
	}
	
	@Test // Sem essa tag não roda o teste kkkk
	void test_delete_withInvaliId_shouldThrowException() {
		//Given
		var id = "001";
		
		//Mock Definitions
		Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.empty());	
		
		try {
			//Test
			unit.get(id);
			fail("Expected NotFoundException");
		} catch (NotFoundException ex) {
			//Assert
			Assertions.assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");
		}
				
		//Verify
		Mockito.verify(employeeRepository).findById(id);
		Mockito.verifyNoMoreInteractions(employeeRepository); //Esta garantindo que ele não foi chamado, se chamr esta errado a logica
	}
	
	@Test
	void test_getAll() {
		//Given
		var lista = new ArrayList<Employee>();
		lista.add(new Employee().withId("0001").withFirstName("Pedro"));
		
		//Mock Definitions
		Mockito.when(employeeRepository.findAll()).thenReturn(lista);
		
		//Test: 
		var result = unit.getAll();
		
		//Assert:
		Assertions.assertEquals(result, lista);
		
		//Verify:
		Mockito.verify(employeeRepository).findAll();
	}

	@Test
	void test_create() {
		//Given
		var employee = new Employee();
		
		//Mock Definitions || Não faz leitura, não precisa
		
		
		//Test
		var result = unit.create(employee);
		
		//Assert || Garantido que o employee foi criado
		assertNotNull(result.getCreateAt());
		assertNotNull(result.getModifiedAt());
		
		//Verify
		Mockito.verify(employeeRepository).save(employee);
	}
	
	@Test
	void test_update_withValidId_shouldUpdateOk() {
		//Given
		var id = "0001";
		var existing = new Employee();
		existing.setFirstName("João");
		existing.setLastName("Silva");
		existing.setDateOfBirth(LocalDate.of(2020, 10, 11));
		existing.setGender(Gender.MALE);
		existing.setStartDate(LocalDate.of(2021, 1, 11));
		existing.setEndDate(LocalDate.of(2021, 9, 20));
		existing.setPosition("System Analyst");
		existing.setMonthlySalary(3500.0f);
		existing.setHourSalary(20.0f);
		existing.setArea("Projects");
		existing.setCreateAt(LocalDateTime.now().minusDays(1));
		existing.setModifiedAt(LocalDateTime.now().minusDays(1));
		
		var updated = new Employee();
		updated.setFirstName("Luisa");
		updated.setLastName("Silva");
		updated.setDateOfBirth(LocalDate.of(2006, 10, 11));
		updated.setGender(Gender.FEMALE);
		updated.setStartDate(LocalDate.of(2010, 1, 11));
		updated.setEndDate(LocalDate.of(2021, 9, 20));
		updated.setPosition("System Analyst");
		updated.setMonthlySalary(4500.0f);
		updated.setHourSalary(20.0f);
		updated.setArea("Projects");
		
		//Mock Definitions
		Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(existing));
		
		//Test
		var result = unit.update(id, updated);
		
		//Assert
		assertEquals(result.getFirstName(), updated.getFirstName());
		assertEquals(result.getLastName(), updated.getLastName());
		assertEquals(result.getDateOfBirth(), updated.getDateOfBirth());
		assertEquals(result.getGender(), updated.getGender());
		assertEquals(result.getStartDate(), updated.getStartDate());
		assertEquals(result.getEndDate(), updated.getEndDate());
		assertEquals(result.getPosition(), updated.getPosition());
		assertEquals(result.getMonthlySalary(), updated.getMonthlySalary());
		assertEquals(result.getHourSalary(), updated.getHourSalary());
		assertEquals(result.getArea(), updated.getArea());
		assertTrue(result.getModifiedAt().isAfter(result.getCreateAt()));

		//Verify
		Mockito.verify(employeeRepository).findById(id);
		Mockito.verify(employeeRepository).save(existing);
	}
	
	@Test
	void test_update_withInvalidId_shouldThrowExecption() {
		//Given
		var id = "0001";
		var upadted = new Employee();
		
		//Mock Definitions
		Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.empty());
		
		try {
			//Test
			unit.update(id, upadted);
			fail("Expection NotFoundException");
		} catch (NotFoundException ex){
			//Assert
			assertEquals(ex.getMessage(), "Employee with ID " + id + " not found");
		}
				
		//Verify
		Mockito.verify(employeeRepository).findById(id);
		Mockito.verifyNoMoreInteractions(employeeRepository);
	}
}
