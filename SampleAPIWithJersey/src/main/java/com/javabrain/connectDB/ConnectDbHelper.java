package com.javabrain.connectDB;

import java.sql.Connection;

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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn_;
	}
}
