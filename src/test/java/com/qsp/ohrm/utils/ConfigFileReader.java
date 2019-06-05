package com.qsp.ohrm.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyFilePath= "config//config.properties";
	FileReader reader=null;
	
	public ConfigFileReader(){
		
		try {
			reader = new FileReader(propertyFilePath);
			properties = new Properties();
			try {
				properties.load(reader);
				//reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	
	public String getBrowserType(){
		String driverPath = properties.getProperty("browser");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("browser not specified in the Configuration.properties file.");		
	}
	
	
	public String getNodeUrl(){
		String nodeUrl = properties.getProperty("nodeUrl");
		if(nodeUrl!= null) return nodeUrl;
		else throw new RuntimeException("nodeUrl not specified in the Configuration.properties file.");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	public String getUserName(){
		String username = properties.getProperty("username");
		if(username != null) return username;
		else throw new RuntimeException("username not specified in the Configuration.properties file.");

	}
	
	public String getPasswordName(){
		String password = properties.getProperty("password");
		if(password != null) return password;
		else throw new RuntimeException("password not specified in the Configuration.properties file.");

	}
	public String getFirstName() {
		String firstname = properties.getProperty("FirstName");
		if(firstname!= null) return firstname;
		else throw new RuntimeException("FirstName is not specified in the config file");
	}
	public String getLastName() {
		String lastname = properties.getProperty("LastName");
		if(lastname!=null) return lastname;
		else throw new RuntimeException("Lastname is not specifed in the config file");
	}
	public String getemployeeID() {
		String employeeID = properties.getProperty("employeeID");
		if(employeeID!=null) return employeeID;
		else throw new RuntimeException("The employeeID is not specified in the config file");
	}
}