package com.foodfarm.street;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class StreetApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreetApplication.class, args);
	}

}
