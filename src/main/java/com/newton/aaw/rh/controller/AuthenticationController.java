package com.newton.aaw.rh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newton.aaw.rh.api.LoginDto;
import com.newton.aaw.rh.api.UserDto;
import com.newton.aaw.rh.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	
	@PostMapping("/auth/login")
	public UserDto login(@RequestBody LoginDto login) {
		// realizar a autenticacao
		var dto = authenticationService.login(
										login.getUserName(),
										login.getPassword());
		return dto;
	}
	
	@PostMapping("/auth/logout")
	public ResponseEntity<Void> logout(@RequestBody LoginDto logout) {
		authenticationService.logout(logout.getUserName());
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
