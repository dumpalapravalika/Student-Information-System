package com.hexaware.sis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {

    // Establishes and returns a DB connection using properties
	public static Connection getConnection(Properties props) {
	    String url = props.getProperty("db.url");
	    String username = props.getProperty("db.username");
	    String password = props.getProperty("db.password");

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
	        Connection conn = DriverManager.getConnection(url, username, password);
	        System.out.println("Connected to Database!");
	        return conn;
	    } catch (ClassNotFoundException e) {
	        System.out.println("JDBC Driver not found: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Database connection failed: " + e.getMessage());
	    }
	    return null;
	}

}

