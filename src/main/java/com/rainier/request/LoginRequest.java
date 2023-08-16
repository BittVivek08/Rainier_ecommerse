package com.rainier.request;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
public class LoginRequest {
	
	private String email;
	
	private String password;

}
