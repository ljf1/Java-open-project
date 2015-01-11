package com.cug.user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
	public static Properties prop = null;
	
	static{
		InputStream in = UserDaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
		prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UserDao getUserDao(){
		String daoClassName = prop.getProperty("com.cug.user.dao.UserDao");
		try {
			Class clazz = Class.forName(daoClassName);
			return (UserDao)clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
