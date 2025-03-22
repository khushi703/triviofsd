package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = "com.example.model")
@SpringBootApplication
public class SdpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdpApplication.class, args);
	}

}
