package com.newton.aaw.rh.api;

import java.time.LocalDateTime;

import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.domain.enums.Role;
import com.newton.aaw.rh.domain.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Esta convertendo as informacoes do microsservico pro cliente
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String id;
	private String name;	
	private String password;
	private String email;
	private String mobile;
	private Status status;
	private Role role;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	
	//Construtor Entidade para Dto. Pega o Dto e passa pra User.
	public UserDto(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.password = u.getPassword();
		this.email = u.getEmail();
		this.mobile = u.getMobile();
		this.status = u.getStatus();
		this.role = u.getRole();
		
		this.createdAt = u.getCreatedAt();
		this.modifiedAt = u.getModifiedAt();
	}
}
