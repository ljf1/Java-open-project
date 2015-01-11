package com.cug.user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtils {
	private static Properties props = null;
	
	static{
		InputStream in = jdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
		props = new Properties();
		try {
			props.load(in);
			Class.forName(props.getProperty("driverClassName"));
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(props.getProperty("url"),
				props.getProperty("username"), props.getProperty("password"));		
	}
}
