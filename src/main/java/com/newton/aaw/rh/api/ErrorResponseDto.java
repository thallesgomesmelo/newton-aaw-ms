package com.newton.aaw.rh.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
	
	private String timestamp;
	private int status;
	private String error;
	private String path;
}
