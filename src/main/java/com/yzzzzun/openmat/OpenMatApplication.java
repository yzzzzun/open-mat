package com.yzzzzun.openmat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OpenMatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenMatApplication.class, args);
	}

}
