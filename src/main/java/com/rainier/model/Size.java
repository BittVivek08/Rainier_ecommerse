package com.rainier.model;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class Size {
	
	private String name;
	
	private int quantity;

}
