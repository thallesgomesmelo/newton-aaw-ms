package com.newton.aaw.rh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class TokenExpiredException extends RuntimeException{

	public TokenExpiredException() {
		super ("Token has expired!");
	}
}
