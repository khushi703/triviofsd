package com.examease.sdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = "com.examease.sdp.model")
@SpringBootApplication
public class SdpApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdpApplication.class, args);
	}

}
