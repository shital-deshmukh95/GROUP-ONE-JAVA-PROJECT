package com.lt.crs.jpaapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.lt.crs.config.ConfigurationJPA;

@SpringBootApplication
@ComponentScan("com.lt.crs.*")
@EnableAutoConfiguration
@EnableWebMvc
@Import(ConfigurationJPA.class)
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

}
