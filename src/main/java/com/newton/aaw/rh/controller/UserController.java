package com.newton.aaw.rh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newton.aaw.rh.api.UserDto;
import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.exception.NotFoundException;
import com.newton.aaw.rh.service.UserService;

@RestController
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public List<UserDto> getAll() {
		var users = userService.getAll();
		var userDtos = new ArrayList<UserDto>();
		
		for(var user: users) {
			userDtos.add(new UserDto(user));
		}
		
		return userDtos;
	}
	
	@PostMapping("/users")
	public UserDto create(@RequestBody UserDto u) {
		var user = new User(u);
		
		user = userService.create(user);
		
		return new UserDto(user);
	}
	
	@PutMapping("/users/{id}")
	public UserDto update(@PathVariable Integer id, @RequestBody UserDto u) throws NotFoundException {
		var user = new User(u);
		
		user = userService.update(id, user);
		
		return new UserDto(user);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws NotFoundException {
		userService.delete(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
