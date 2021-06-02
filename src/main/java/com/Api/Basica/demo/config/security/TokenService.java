package com.Api.Basica.demo.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.Api.Basica.demo.modelo.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService  {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication auth) {
		Usuario logado = (Usuario)auth.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date( hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("API Forum alura") // quem foi que criou esse token
				.setSubject(logado.getId().toString())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	
	}

	public static boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token) ;
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

}
