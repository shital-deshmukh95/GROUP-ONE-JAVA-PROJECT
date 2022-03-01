package com.lt.crs.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.lt.crs.config.ConfigurationJPA;

/**
 * @author user215
 *
 */
@SpringBootApplication
@ComponentScan("com.lt.crs.*")
@EnableAutoConfiguration
@EnableWebMvc
@Import(ConfigurationJPA.class)
public class CrsLtSpringRestJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsLtSpringRestJpaApplication.class, args);
	}

}
