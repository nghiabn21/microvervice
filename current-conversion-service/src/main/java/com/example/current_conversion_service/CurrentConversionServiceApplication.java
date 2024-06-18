package com.example.current_conversion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrentConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrentConversionServiceApplication.class, args);
	}

}
