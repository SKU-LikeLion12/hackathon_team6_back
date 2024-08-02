package com.feelinsight.feelinsight;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@OpenAPIDefinition(servers = {@Server(url = "https://team6back.sku-sku.com/", description = "Default Server URL")})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FeelinsightApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeelinsightApplication.class, args);
	}

}
