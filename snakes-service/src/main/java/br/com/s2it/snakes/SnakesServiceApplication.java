package br.com.s2it.snakes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("br.com.s2it.snakes.repositories")
public class SnakesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnakesServiceApplication.class, args);
	}
}
