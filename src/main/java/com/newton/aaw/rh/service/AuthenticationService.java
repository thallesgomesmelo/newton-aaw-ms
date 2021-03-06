package com.newton.aaw.rh.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.newton.aaw.rh.api.UserDto;
import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.domain.repository.UserRepository;
import com.newton.aaw.rh.exception.BadRequestException;
import com.newton.aaw.rh.exception.NotAuthorizedException;
import com.newton.aaw.rh.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final TokenService tokenService;
	
	public UserDto login(String userName, String password) {
		// 0. Quando usuario e senha vazio
		if(userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			throw new BadRequestException("Parametros inválidos.");
		}
		
		// 1. verificar o nome do usuario		
		var user = userRepository.findOneByName(userName);
		if (user.isEmpty()) {
			throw new NotAuthorizedException("User with name " + userName + " not found!");
		}
		
		// 2. verificar a senha informada
		var userExists = user.get();
		if (!userExists.getPassword().equals(password)) {
			throw new NotAuthorizedException("User " + userName + " password incorrect!");
		}
		
		// 3. atualizar as informacoes de login/logout
		userExists.setLoggedInAt(LocalDateTime.now());
		userExists.setLoggedOutAt(null); //Usado pra falar que o usuario esta logado

		userRepository.save(userExists);
		
		//adicionar a geracao do token JWT. Convertendo o user em um UserDto
		var token = tokenService.generateToken(userExists);
		var userDto = new UserDto(userExists);
		userDto.setToken(token);
		
		return userDto;		
	}
	
	public User logout(String userName) {
		// 1. verificar se usuario existe		
		var user = userRepository.findOneByName(userName);
		if (user.isEmpty()) {
			throw new NotFoundException("User with name " + userName + " not found!");
		}
		
		var userExists = user.get();

		// 2. atualizar as informacoes de logout
		userExists.setLoggedOutAt(LocalDateTime.now());

		userRepository.save(userExists);
		
		return userExists;		
	}	
}
