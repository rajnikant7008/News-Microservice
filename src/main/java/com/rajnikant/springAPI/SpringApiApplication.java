package com.rajnikant.springAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiApplication.class, args);
	}
}
