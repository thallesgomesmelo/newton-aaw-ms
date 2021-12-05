package com.newton.aaw.rh.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.exception.BadRequestException;
import com.newton.aaw.rh.exception.TokenExpiredException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 1. criar o token do zero: login
 * 2. validar o token: autorizacao, prazo
 * 3. refresh token: atualiza o token gerado (+5m)
 * 4. decodificacao do token: apoio (helper)
 * 
 * @author Thalles
 *
 */

@Service
public class TokenService {
	
	//5 minutos
	public static final int EXPIRATION_TIME_MS = 5 * 60 * 1000;
	
	private static final String SECRET = "chaveAleatoria123";
	
	//Etapa 1
	public String generateToken(User user) {
		var exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS);
		
		String token = generateToken(user, exp);
		
		return token;
	}

	//Criou outro metodo que contem o parametro de data de expiracao
	String generateToken(User user, Date exp) {
		String token = Jwts.builder()
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setSubject(user.getName().concat(":").concat(user.getEmail()))
			.setExpiration(exp)
			.signWith(SignatureAlgorithm.HS256, SECRET)
			.compact();
		
		return token;
	}
	
	//Etapa 4
	public Claims decodeToken(String token) {
		return Jwts.parser()
			.setSigningKey(SECRET)
			.parseClaimsJws(token)
			.getBody();
	}
	
	//Etapa 2
	public void validate(String token) {
		if(token == null || token.trim().isEmpty()) {
			throw new BadRequestException("JWT token is invalid!");
		}
		
		// Tem que tirar o prefisco 'Bearer'
		String tokenTratado = token.replace("Bearer ", "");
		
		try {
			var claims = this.decodeToken(tokenTratado);
			
			System.out.println("exp: " + claims.getExpiration());						
			System.out.println("Token validado com sucesso!");
			
		} catch(ExpiredJwtException ex) {
			System.out.println(ex);
			throw new TokenExpiredException();
			
		} catch(Exception ex) {
			System.out.println(ex);			
			throw new BadRequestException("JWT token is invalid!");
		}
	}
}
