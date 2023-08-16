package com.rainier.response;



import lombok.AllArgsConstructor;
import lombok.Data;



@Data
public class AuthResponse {
	
	private String jwt;
	
	private String message;

}
