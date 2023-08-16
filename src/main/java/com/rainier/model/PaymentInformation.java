package com.rainier.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;

@Component
public class PaymentInformation {
	
	@Column(name = "cardholder_name")
	private String cardHolderName;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "expiration_date")
	private LocalDate expirationDate;
	
	@Column(name = "cvv")
	private String cvv;

}
