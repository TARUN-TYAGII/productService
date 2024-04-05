package com.example.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ProductServiceApplication.class);
		Environment env = app.run(args).getEnvironment();
		String port = env.getProperty("server.port");
		System.out.println("Server running on port: " + port);
	}

}
