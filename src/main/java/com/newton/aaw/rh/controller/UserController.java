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
import com.newton.aaw.rh.service.UserService;

//Controller so reconhece a classe service.
@RestController
public class UserController {

	//Criou UserService pra poder chamar a classe UserService
	private final UserService userService;
	
	//Esta pegando o serviço que criou em cima e esta injetando nesta classe pra poder usar ele.
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//O pathVariable esta pegando o id do prametro e enviando pro id da rota no getMapping.
	@GetMapping("/users/{id}")
	public UserDto getById(@PathVariable String id) {
		var user = userService.get(id);
		
		return new UserDto(user);
	}
	
	@GetMapping("/users")
	public List<UserDto> getAll() {
		var users = userService.getAll();
		var userDtos = new ArrayList<UserDto>();
		
		//Pecorre os usuario e vai adicionando a lista.
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
	public UserDto update(@PathVariable String id, @RequestBody UserDto u) {
		var user = new User(u);
		
		user = userService.update(id, user);
		
		return new UserDto(user);
	}
	
	//Delete tem que responder codigo 204 do http 
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
