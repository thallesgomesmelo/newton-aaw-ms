package com.newton.aaw.rh.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.newton.aaw.rh.api.UserDto;
import com.newton.aaw.rh.domain.enums.Gender;

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
	private LocalDateTime createdAt; //Usuario não envia esse dado, só o banco de dados por isso não usou na classe a baixo.
	private LocalDateTime modifiedAt; //Usuario não envia esse dado, só o banco de dados por isso não usou na classe a baixo.
	private Gender status;
	private Gender role;	

	//Esta pegando um Dto e transformando em um User. User que ler é o banco de dados.
	public User(UserDto userDto) {
		this.id = userDto.getId();
		this.name = userDto.getName();
		this.password = userDto.getPassword();
		this.email = userDto.getEmail();
		this.mobile = userDto.getMobile();		
	}
}