package com.Api.Basica.demo.Dto;

public class TokenDto {
	
	private String token;
	private String field;
	
	public TokenDto(String token, String field) {
		this.token = token;
		this.field = field;
		
	}

	public String getToken() {
		return token;
	}

	public String getField() {
		return field;
	}
	

}
