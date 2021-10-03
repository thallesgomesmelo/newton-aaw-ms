package com.newton.aaw.rh.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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

import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.domain.enums.Role;
import com.newton.aaw.rh.domain.enums.Status;
import com.newton.aaw.rh.domain.repository.UserRepository;
import com.newton.aaw.rh.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	
	private UserService unit;
	
	@BeforeEach
	public void setup() {
		unit = new UserService(userRepository);
	}
	
	@Test
	void test_getById_withUser_shouldReturnObj() {
		//Given:
		var user = new User();
		user.setId("user01");
		
		//Mock definitions: 
		Mockito.when(userRepository.findById("user01")).thenReturn(Optional.of(user));
		
		//Test: 
		var result = unit.get("user01");
		
		//Assert:
		Assertions.assertEquals(result, user);
		
		//Verify:
		Mockito.verify(userRepository).findById("user01");
	} 
	
	@Test
	void test_getById_withNoUser_shouldThrowException() {
		//Given
		var id = "user01";
		
		//Mock definitions
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		
		//Test
		try {
			unit.get(id);
			fail("Expected NotFoundException");
		} catch (NotFoundException ex){
			//Assert
			assertEquals(ex.getMessage(), "User with ID " + id + " not found");
		}
		
		//Verify
		Mockito.verify(userRepository).findById(id);		
	}

	@Test
	void test_delete_withValidaId_shouldDeletOk() {
		//Given
		var id = "0001";
		var user = new User();
		
		//Mock definitions
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
		
		//Test
		unit.delete(id);
		
		//Assert
		
		//Verify
		Mockito.verify(userRepository).findById(id);
		Mockito.verify(userRepository).deleteById(id);
	}
	
	@Test
	void test_delete_withInvaliId_shouldThrowException() {
		//Given
		var id = "0001";
		
		//Mock Definitions
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());	
		
		try {
			//Test
			unit.get(id);
			fail("Expected NotFoundException");
		} catch (NotFoundException ex) {
			//Assert
			Assertions.assertEquals(ex.getMessage(), "User with ID " + id + " not found");
		}
				
		//Verify
		Mockito.verify(userRepository).findById(id);
		Mockito.verifyNoMoreInteractions(userRepository); 
	}
	
	@Test
	void test_getAll() {
		//Given
		var lista = new ArrayList<User>();
		lista.add(new User());
		
		//Mock Definitions
		Mockito.when(userRepository.findAll()).thenReturn(lista);
		
		//Test: 
		var result = unit.getAll();
		
		//Assert:
		Assertions.assertEquals(result, lista);
		
		//Verify:
		Mockito.verify(userRepository).findAll();
	}
	
	@Test
	void test_create() {
		//Given
		var user = new User();		
		
		//Test
		var result = unit.create(user);
		
		//Assert 
		assertNotNull(result.getCreatedAt());
		assertNotNull(result.getModifiedAt());
		
		//Verify
		Mockito.verify(userRepository).save(user);
	}
	
	@Test 
	void test_update_withValidId_shouldUpdateOk() {
		//Given
		var id = "0001";
		var existing = new User();
		existing.setName("Pedro");
		existing.setPassword("23456");
		existing.setEmail("pedro..d@gmail.com");
		existing.setMobile("31 96542 5475");
		existing.setStatus(Status.ACTIVE);
		existing.setRole(Role.ADMIN);
		existing.setCreatedAt(LocalDateTime.now().minusDays(1));
		existing.setModifiedAt(LocalDateTime.now().minusDays(1));
		
		var updated = new User();
		updated.setName("Lucas");
		updated.setPassword("2345");
		updated.setEmail("lucas.d@gmail.com");
		updated.setMobile("31 96852 5475");
		updated.setStatus(Status.INACTIVE );
		updated.setRole(Role.READ);
		updated.setModifiedAt(LocalDateTime.now().minusDays(1));
		
		//Mock definitions
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(existing));
		
		//Test
		var result = unit.update(id, updated);
		
		//Assert
		assertEquals(result.getName(), updated.getName());
		assertEquals(result.getPassword(), updated.getPassword());
		assertEquals(result.getEmail(), updated.getEmail());
		assertEquals(result.getMobile(), updated.getMobile());
		assertEquals(result.getStatus(), updated.getStatus());
		assertEquals(result.getRole(), updated.getRole());
		assertTrue(result.getModifiedAt().isAfter(result.getCreatedAt()));
		
		//Verify
		Mockito.verify(userRepository).findById(id);
		Mockito.verify(userRepository).save(existing);
	}
	
	@Test
	void test_update_withInvalidId_shouldThrowExecption() {
		//Given
		var id = "0001";
		var updated = new User();
		
		//Mock definitions
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		
		try {
			//Test
			unit.update(id, updated);
			fail("Expection NotFoundException");
		} catch (NotFoundException ex) {
			//Assert
			Assertions.assertEquals(ex.getMessage(), "User with ID " + id + " not found");
		}
		
		//Verify
		Mockito.verify(userRepository).findById(id);
		Mockito.verifyNoMoreInteractions(userRepository);
		
	}
}
