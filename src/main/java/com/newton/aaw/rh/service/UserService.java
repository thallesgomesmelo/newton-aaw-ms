package com.newton.aaw.rh.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.domain.repository.UserRepository;
import com.newton.aaw.rh.exception.NotFoundException;

@Service
public class UserService {
	//Pq nao tem banco de dados criou um mapa de objeto em memoria, mapa de chave valor
	//	private Map<String, User> users = new HashMap<String, User>();
	
	//Usando agora o Banco de dados
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//C - CRUD(Criando)
	public User create(User u) {
		u.setCreatedAt(LocalDateTime.now());
		u.setModifiedAt(LocalDateTime.now());
		
		userRepository.save(u);
		return u;
	}
	
	//U - CRUD(Atualizar)
	public User update(String id, User u) {
		//Recuperar para validar se existe
		var existing = get(id);
		
		//Update
		existing.setName(u.getName());
		existing.setPassword(u.getPassword());
		existing.setEmail(u.getEmail());
		existing.setMobile(u.getMobile());
		
		existing.setModifiedAt(LocalDateTime.now());
		
		userRepository.save(existing);
		return existing;
	}
	
	// R - CRUD(Lendo)
	public User get(String id) {
		
		var user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new NotFoundException("User with ID " + id + " not found"); 
		}
		
		return user.get();
	}
	
	//R - CRUD(Lendo todos, getAll)
	public List<User> getAll() {
		return userRepository.findAll();		
	}
	
	//D - CRUD(Deleta)
	public void delete(String id) {
		//Recuperar para validar se existe
		get(id);
	
		userRepository.deleteById(id);
	}
}
