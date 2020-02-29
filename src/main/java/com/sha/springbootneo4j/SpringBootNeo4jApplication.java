package com.sha.springbootneo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//If we don't specify it, It will use application-default.properties.
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class SpringBootNeo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNeo4jApplication.class, args);
	}

}
