package com.newton.aaw.rh.domain.entity;

import java.time.LocalDateTime;

import com.newton.aaw.rh.api.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;
	private String name;	
	private String password;
	private String email;
	private String mobile;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	

	public User(UserDto userDto) {
		this.id = userDto.getId();
		this.name = userDto.getName();
		this.password = userDto.getPassword();
		this.email = userDto.getEmail();
		this.mobile = userDto.getMobile();		
	}
}

