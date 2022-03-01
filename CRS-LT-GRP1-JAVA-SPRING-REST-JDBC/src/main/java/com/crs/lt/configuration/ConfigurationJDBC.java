package com.crs.lt.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.crs.lt.business.RegistrationInterface;
import com.crs.lt.dao.RegistrationDaoOperation;

@Configuration
public class ConfigurationJDBC {
	@Bean
	  public DataSource dataSource() {
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      System.out.println("Inside DataSource");
	      //MySQL database we are using
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/crs");//change url
	      dataSource.setUsername("root");//change userid
	      dataSource.setPassword("root");//change pwd
	      //H2 database
	/*
	   dataSource.setDriverClassName("org.h2.Driver");
	   dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
	   dataSource.setUsername("sa");
	   dataSource.setPassword("");*/
	      System.out.println("DataSource returned");
	      return dataSource;
	  }
	
//	@Bean
//	public RegistrationDaoOperation registerDao() {
//		 //RegistrationDaoOperation registrationInterface = new RegistrationDaoOperation();
//	RegistrationDaoOperation registrationDaoOperation = new RegistrationDaoOperation();
//	registrationDaoOperation.setDataSource(dataSource());
//	return registrationDaoOperation;
//	}

}
