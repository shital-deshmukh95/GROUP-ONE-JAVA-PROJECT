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
	
	      System.out.println("DataSource returned");
	      return dataSource;
	  }
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

}
