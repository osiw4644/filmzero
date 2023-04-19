package com.zeromovie.filmzero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FilmzeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmzeroApplication.class, args);
	}

}
