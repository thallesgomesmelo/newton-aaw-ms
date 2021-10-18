package com.newton.aaw.rh.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.domain.repository.UserRepository;
import com.newton.aaw.rh.exception.NotAuthorizedException;
import com.newton.aaw.rh.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	
	
	public User login(String userName, String password) {
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
		userExists.setLoggedOutAt(null);

		userRepository.save(userExists);
		
		return userExists;		
	}
	
	public User logout(String userName) {
		// 1. verificar o nome do usuario		
		var user = userRepository.findOneByName(userName);
		if (user.isEmpty()) {
			throw new NotFoundException("User with name " + userName + " not found!");
		}
		
		var userExists = user.get();

		// 2. atualizar as informacoes de login/logout
		userExists.setLoggedOutAt(LocalDateTime.now());

		userRepository.save(userExists);
		
		return userExists;		
	}	
}
