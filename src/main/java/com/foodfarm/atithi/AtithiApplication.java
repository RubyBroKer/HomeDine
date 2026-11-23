package com.foodfarm.atithi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class AtithiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtithiApplication.class, args);
	}

}
