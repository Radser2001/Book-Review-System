package com.radser2001.books;

import org.springframework.boot.SpringApplication;
//annotation that tells Spring Boot to “guess” how you will want to configure Spring, based on the jar dependencies that you have added
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class BooksApplication {

	public static void main(String[] args) {

		// run the application
		SpringApplication.run(BooksApplication.class, args);

	}

}
