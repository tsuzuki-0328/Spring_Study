package com.jutjoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MynewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MynewsApplication.class, args);
	}

}
