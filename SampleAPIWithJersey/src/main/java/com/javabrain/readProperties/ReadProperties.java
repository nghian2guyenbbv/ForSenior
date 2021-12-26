package com.javabrain.readProperties;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties {
	private String userDB;
	private String passwordDB;
	private String urlDB;

	public ReadProperties() {
		getProperties();
	}

	public String getUserDB() {
		return userDB;
	}

	public void setUserDB(String userDB) {
		this.userDB = userDB;
	}

	public String getPasswordDB() {
		return passwordDB;
	}

	public void setPasswordDB(String passwordDB) {
		this.passwordDB = passwordDB;
	}

	public String getUrlDB() {
		return urlDB;
	}

	public void setUrlDB(String urlDB) {
		this.urlDB = urlDB;
	}

private void getProperties() {
	try {
		/*
		 * URL url = ClassLoader.getSystemResource("."); File me = new
		 * File(url.getFile()); return me.getAbsolutePath();
		 */
	  FileInputStream files = new FileInputStream("./resources/connectDb.properties");
	  Properties prop = new Properties();
	  prop.load(files);
	  String userDB = prop.getProperty("userDB");
	  String passDB = prop.getProperty("passwordDB");
	  String urlDB = prop.getProperty("urlDB");
	  setUrlDB(urlDB);
	  setUserDB(userDB);
	  setPasswordDB(passDB);
	}catch(Exception ex) {
		ex.printStackTrace();
	}
}
}
