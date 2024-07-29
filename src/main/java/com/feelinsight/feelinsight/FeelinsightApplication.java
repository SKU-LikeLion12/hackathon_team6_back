package com.feelinsight.feelinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FeelinsightApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeelinsightApplication.class, args);
	}

}
