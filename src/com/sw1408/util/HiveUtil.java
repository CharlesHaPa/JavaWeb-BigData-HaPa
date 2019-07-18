package com.sw1408.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.sw1408.util.PropertiesUtil;

public class HiveUtil {
	
private Connection connection;
	
	private Statement statement;
	
	public HiveUtil() {
		init("default");
	}
	public HiveUtil(String db) {
		init(db);
	}
	public void init(String db){
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		Properties properties = propertiesUtil.getProperties("/system.properties");
		String driverName = properties.getProperty("hive.driverClass");
		String url = properties.getProperty("hive.jdbcurl")+db;
		String userName = properties.getProperty("mysql.user");
		String password = properties.getProperty("mysql.password");
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, userName, password);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeCommand(String cmd){
		try {
			statement.execute(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet executeQuery(String cmd){
		try {
			return statement.executeQuery(cmd);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setDatabase(String database){
		executeCommand("use " + database);
	}

}