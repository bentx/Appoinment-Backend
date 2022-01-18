package com.tactopus.appoinment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class AppoinmentBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppoinmentBackendApplication.class, args);
	}

}
