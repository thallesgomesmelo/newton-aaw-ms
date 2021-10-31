package com.newton.aaw.rh.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.exception.BadRequestException;
import com.newton.aaw.rh.exception.TokenExpiredException;

class TokenServiceTest {

	private TokenService unit;
	
	@BeforeEach
	public void setup() {
		unit = new TokenService()
;	}
	
	@Test
	void test_generationToken_withValidUser_shouldGenerateOk() {
		//given:
		var user = new User();
		user.setName("joao");
		user.setEmail("joao@gmail.com");
		
		//test:
		var token = unit.generateToken(user);
		
		//assert:
		assertNotNull(token);
		var claims = unit.decodeToken(token);
		assertEquals("joao:joao@gmail.com", claims.getSubject());
	}
	
	@Test
	void test_validate_withValidToken_shoulThrowNoExceptions() {
		//given: Criando o token
		var user = new User();
		user.setName("joao");
		user.setEmail("joao@gmail.com");
		
		var token = unit.generateToken(user);
		
		//test:
		unit.validate("Bearer " + token);		
	}
	
	@Test
	void test_validate_withExpiredToken_shoulThrowTokenExpiredExceptions() {
		//given: Criando o token
		var user = new User();
		user.setName("joao");
		user.setEmail("joao@gmail.com");
		
		//Criando um token ja expirado
		var exp = new Date(System.currentTimeMillis() - 2000);
		
		var token = unit.generateToken(user, exp);
		
		try {
			//test:
			unit.validate("Bearer " + token);
			fail("Expected TokenExpiredException");
		} catch(TokenExpiredException ex) {
			//assert:
			assertEquals("Token has expired!", ex.getMessage());
		}
	}
	
	@Test
	void test_validate_withInvalidToken_shoulThrowBadRequestExceptions() {
		//given: 		
		var token = "invalid";
		
		try {
			//test:
			unit.validate("Bearer " + token);
			fail("Expected BadRequestException");
		} catch(BadRequestException ex) {
			//assert:
			assertEquals("JWT token is invalid!", ex.getMessage());
		}
	}
}
