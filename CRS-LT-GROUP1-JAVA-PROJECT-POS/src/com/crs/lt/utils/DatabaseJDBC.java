package com.crs.lt.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseJDBC {
private static Connection connection = null;
	
	public static Connection getConnection() {
		
        if (connection != null) {
        	try {
        		if (connection.isClosed()) {
                	//System.out.println("Connection was closed...");
                	connection = null;
                	return getConnection();
                } else {
                	//System.out.println("Connection good...");
                	return connection;
                }
        	} catch (SQLException e) {
        		//System.out.println("Error2345: " + e.getMessage());
        		//e.printStackTrace();
        		//connection = null;
        		return getConnection();
        	}
        } else {
            try {
            	//System.out.println("Connection was NULL...");
            	Properties prop = new Properties();
                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                
            } catch (Exception e) {
                e.printStackTrace();
            
            return connection;
        }

    }
		return connection;


	}
}
