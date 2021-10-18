package com.newton.aaw.rh.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.domain.repository.UserRepository;
import com.newton.aaw.rh.exception.NotAuthorizedException;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

	@Mock
	private UserRepository userRepository;
	
	private AuthenticationService unit;
	
	@BeforeEach
	public void setup() {
		unit = new AuthenticationService(userRepository);
	}
	
	@Test
	void test_login_withUsernameNotFound_shouldThrowException() {
		// given
		var name = "testUser";
		
		// mock definitions
		Mockito.when(userRepository.findOneByName(name))
			.thenReturn(Optional.empty());
		
		try {
			// test
			unit.login(name, null);
			
			fail("Expected NotAuthorizedException");			
		} catch (NotAuthorizedException ex) {
			// assert
			assertEquals("User with name " + name + " not found!", ex.getMessage());
		}
		
		// verify
		Mockito.verify(userRepository).findOneByName(name);
		Mockito.verifyNoMoreInteractions(userRepository);		
	}


	@Test
	void test_login_withIncorrectPassword_shouldThrowException() {
		// given
		var name = "testUser";
		var user = new User();
		user.setPassword("123456");
		
		// mock definitions
		Mockito.when(userRepository.findOneByName(name))
			.thenReturn(Optional.of(user));
		
		try {
			// test
			unit.login(name, "654321");
			
			fail("Expected NotAuthorizedException");			
		} catch (NotAuthorizedException ex) {
			// assert
			assertEquals("User " + name + " password incorrect!", ex.getMessage());
		}
		
		// verify
		Mockito.verify(userRepository).findOneByName(name);
		Mockito.verifyNoMoreInteractions(userRepository);
	}

	@Test
	void test_login_withCorrectPassword_shouldReturnUserUpdated() {
		// given
		var name = "testUser";
		var user = new User();
		user.setPassword("123456");
		
		// mock definitions
		Mockito.when(userRepository.findOneByName(name))
			.thenReturn(Optional.of(user));
		
		// test
		var result = unit.login(name, "123456");
		
		// assert
		assertNotNull(result.getLoggedInAt());
		assertNull(result.getLoggedOutAt());
			
		// verify
		Mockito.verify(userRepository).findOneByName(name);
		Mockito.verify(userRepository).save(user);
	}
}
