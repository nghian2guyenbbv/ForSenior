package com.javabrain.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;

import com.javabrain.readProperties.ReadProperties;

public class ConnectDbHelper {
	private static volatile ConnectDbHelper instance;

	public static ConnectDbHelper getInstance() {
		if (instance == null) {
			synchronized (ConnectDbHelper.class) {
				if (instance == null) {
					instance = new ConnectDbHelper();
				}
			}
		}
		return instance;
	}
	public Connection initConnection() {
		Connection conn_ =null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		ReadProperties properties = new ReadProperties();
		String userDB = properties.getUserDB();
		String urlDB = properties.getUrlDB();
		String password = properties.getPasswordDB();
		conn_ = DriverManager.getConnection(urlDB,userDB,password);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return conn_;
	}
	
}
