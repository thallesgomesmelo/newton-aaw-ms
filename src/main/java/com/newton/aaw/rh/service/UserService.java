package com.newton.aaw.rh.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.exception.NotFoundException;

@Service
public class UserService {

	private static int id = 0;
	//Pq não tem banco de dados criou um mapa de objeto em memória, mapa de chave valor
	private Map<Integer, User> users = new HashMap<Integer, User>();
	
	//C - CRUD(Criando)
	public User create(User u) {
		u.setId(++id);
		
		u.setCreatedAt(LocalDateTime.now());
		u.setModifiedAt(LocalDateTime.now());
		
		users.put(id, u);
		
		return u;
	}
	
	//U - CRUD(Atualizar)
	public User update(Integer id, User u) throws NotFoundException {
		//Recuperar para validar se existe
		var existing = get(id);
		
		//Update
		existing.setName(u.getName());
		existing.setPassword(u.getPassword());
		existing.setEmail(u.getEmail());
		existing.setMobile(u.getMobile());
		
		existing.setModifiedAt(LocalDateTime.now());
		
		return existing;
	}
	
	// R - CRUD(Lendo)
	public User get(Integer id) throws NotFoundException {
		
		var user = users.get(id);
		
		if(user == null) {
			throw new NotFoundException("User with ID " + id + " not found"); 
		}
		
		return user;
	}
	
	//R - CRUD(Lendo todos)
	public List<User> getAll() {
		return new ArrayList<>(users.values());		
	}
	
	//D - CRUD(Deleta)
	public void delete(Integer id) throws NotFoundException {
		//Recuperar para validar se existe
		get(id);
		
		users.remove(id);
	}
}
