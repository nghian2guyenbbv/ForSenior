package com.spring.config;


import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.spring")
@PropertySource("classpath:persistence-oracle.properties")
public class DemoAppConfig {
private	org.apache.logging.log4j.Logger _logger = LogManager.getLogger(DemoAppConfig.class);
			
@Autowired
private  Environment env;
@Bean
public ViewResolver getView() {
	InternalResourceViewResolver viewRsol = new InternalResourceViewResolver();
	viewRsol.setPrefix("/WEB-INF/view/");
	viewRsol.setSuffix(".jsp");
	return viewRsol;
}
@Bean
public DataSource getDataSource() {
	ComboPooledDataSource dataSourceConfig = new ComboPooledDataSource();
	try {
		dataSourceConfig.setDriverClass(env.getProperty("jdbc.driver"));
	} catch (PropertyVetoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	dataSourceConfig.setJdbcUrl(env.getProperty("jdbc.url"));
	dataSourceConfig.setUser(env.getProperty("jdbc.user"));
	dataSourceConfig.setPassword(env.getProperty("jdbc.password"));
	_logger.info("jdbx url: "+env.getProperty("jdbc.url"));
	_logger.info("jdbc user:"+ env.getProperty("jdbc.user"));
	dataSourceConfig.setInitialPoolSize(getIntegerProperty("connection.pool.initialPoolSize"));
	dataSourceConfig.setMinPoolSize(getIntegerProperty("connection.pool.minPoolSize"));
	dataSourceConfig.setMaxPoolSize(getIntegerProperty("connection.pool.maxPoolSize"));
	dataSourceConfig.setMaxIdleTime(getIntegerProperty("connection.pool.maxIdleTime"));
	return dataSourceConfig;
}
private int getIntegerProperty(String propertyName) {
	int propertyValue = 0;
	try {
		 propertyValue = Integer.parseInt(env.getProperty(propertyName));
	}catch(Exception ex){
		ex.printStackTrace();
	}
	return propertyValue;
}
}
