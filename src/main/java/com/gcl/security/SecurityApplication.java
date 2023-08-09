package com.gcl.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableMongoRepositories(basePackages = "com.gcl.dental.core")
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }, scanBasePackages = { "com.gcl.security" })
@EnableRedisHttpSession
//@ComponentScan("com.gcl.dental.core")

//@ComponentScan("com.gcl.security")
//@ComponentScan(basePackages = { "com.gcl.security" })
@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
