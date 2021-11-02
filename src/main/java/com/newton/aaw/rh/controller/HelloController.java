package com.newton.aaw.rh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {
	
	@GetMapping()
	public String hello() {
		return "Hello world!";
	}
	
	//@GetMapping("/jwt")
	//public String helloJwt(@RequestHeader String token) {
		//Validando o token recebido
	//	tokenService.validate(token);
	//	return "JWT OK";
	//}
	
	@GetMapping("/jwt")
	public String helloJwt() {
		return "JWT OK";
	}
}
