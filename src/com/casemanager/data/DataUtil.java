package com.casemanager.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataUtil {

	private static String serverName = "localhost";
	private static String portNumber = "1433";
	private static String userName = "caseadmin";
	private static String password = "CASEADMIN";

	public Connection getConnection() throws SQLException {

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:sqlserver://" 
				+ serverName + ":" + portNumber ,
				connectionProps);
		System.out.println("Connected to database");
		return conn;
	}
	
	public static void main(String[] args) {
		DataUtil dbUtil = new DataUtil();
		try {
			dbUtil.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
