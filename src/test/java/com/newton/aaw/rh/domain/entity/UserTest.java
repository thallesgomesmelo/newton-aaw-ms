package com.newton.aaw.rh.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.newton.aaw.rh.api.UserDto;
import com.newton.aaw.rh.domain.enums.Role;
import com.newton.aaw.rh.domain.enums.Status;

class UserTest {
	
	@Test
	void test_createFromDto_withDto_shouldCreatedOk() {
		//Given 
		var dto = new UserDto();
		dto.setName("Pedro");
		dto.setPassword("23456");
		dto.setEmail("pedro..d@gmail.com");
		dto.setMobile("31 96542 5475");
		dto.setStatus(Status.ACTIVE);
		dto.setRole(Role.ADMIN);
		
		//Test
		var result = new User(dto);

		//Assert
		assertEquals(dto.getName(), result.getName());
		assertEquals(dto.getPassword(), result.getPassword());
		assertEquals(dto.getEmail(), result.getEmail());
		assertEquals(dto.getMobile(), result.getMobile());
		assertEquals(dto.getStatus(), result.getStatus());
		assertEquals(dto.getRole(), result.getRole());
	
		//Verify
	}
	

}
