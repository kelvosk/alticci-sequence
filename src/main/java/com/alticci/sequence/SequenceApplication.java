package com.alticci.sequence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SequenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SequenceApplication.class, args);
	}

}
