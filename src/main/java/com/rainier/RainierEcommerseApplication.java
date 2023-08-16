package com.rainier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class RainierEcommerseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RainierEcommerseApplication.class, args);
	}

}
