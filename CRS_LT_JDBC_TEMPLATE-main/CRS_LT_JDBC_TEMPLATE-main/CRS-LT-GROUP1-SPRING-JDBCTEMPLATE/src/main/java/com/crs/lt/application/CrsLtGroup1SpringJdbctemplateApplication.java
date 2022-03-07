package com.crs.lt.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.crs.lt.configuration.ConfigurationJDBC;
import com.crs.lt.dao.AdminDOAInterface;
import com.crs.lt.dao.AdminDoaOperation;

@ComponentScan({"com.crs.lt.*"})
@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@SpringBootApplication
@Import({ConfigurationJDBC.class})
public class CrsLtGroup1SpringJdbctemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsLtGroup1SpringJdbctemplateApplication.class, args);
		 //ApplicationContext context=SpringApplication.run(CrsLtGroup1SpringJdbctemplateApplication.class, args);
		 
		 //AdminDoaOperation adminJDBCTemplate = context.getBean(AdminDoaOperation.class);
	}

}
