package com.newton.aaw.rh.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.newton.aaw.rh.api.UserDto;
import com.newton.aaw.rh.domain.enums.Role;
import com.newton.aaw.rh.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private String id;
	private String name;	
	private String password;
	private String email;
	private String mobile;
	private Status status;
	private Role role;	
	private LocalDateTime createdAt; //Usuario nao envia esse dado, so o banco de dados por isso nao usou na classe a baixo.
	private LocalDateTime modifiedAt; //Usuario nao envia esse dado, so o banco de dados por isso nao usou na classe a baixo.
	
	// data login
	private LocalDateTime loggedInAt;
		
	// data logout
	private LocalDateTime loggedOutAt;
		
	//Esta pegando um Dto e transformando em um User. User que ler e o banco de dados.
	public User(UserDto userDto) {
		this.name = userDto.getName();
		this.password = userDto.getPassword();
		this.email = userDto.getEmail();
		this.mobile = userDto.getMobile();
		this.status = userDto.getStatus();
		this.role = userDto.getRole();
	}
}