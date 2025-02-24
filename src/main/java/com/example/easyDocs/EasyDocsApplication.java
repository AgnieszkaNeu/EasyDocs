package com.example.easyDocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EasyDocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyDocsApplication.class, args);
	}

}
