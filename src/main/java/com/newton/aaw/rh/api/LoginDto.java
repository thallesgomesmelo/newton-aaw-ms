package com.newton.aaw.rh.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	
	private String userName;
	private String password;
}
