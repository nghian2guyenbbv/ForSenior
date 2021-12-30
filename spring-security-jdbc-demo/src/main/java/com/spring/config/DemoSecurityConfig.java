package com.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource datasource;
 @Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	// TODO Auto-generated method stub
	/*
	 * UserBuilder userBuilder = User.withDefaultPasswordEncoder();
	 * auth.inMemoryAuthentication().withUser(userBuilder.username("nghia").password
	 * ("123").roles("ADMIN","EMPLOYEE"))
	 * .withUser(userBuilder.username("ngan").password("123").roles("EMPLOYEE",
	 * "MYWIFE"))
	 * .withUser(userBuilder.username("ha").password("123").roles("SYSADMIN",
	 * "EMPLOYEE"));
	 */
	 auth.jdbcAuthentication().dataSource(datasource);
 }
 @Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
	http.authorizeRequests()
		.anyRequest().authenticated()
			/*
			 * .antMatchers("/").hasRole("EMPLOYEE")
			 * .antMatchers("/adminMetting/**").hasRole("ADMIN")
			 * .antMatchers("/myWife/**").hasRole("MYWIFE")
			 * .antMatchers("/systemMeeting/**").hasRole("SYSADMIN") .and()
			 */
		.and()
		.formLogin()
		 	.loginPage("/showMyLoginForm")
		 	.loginProcessingUrl("/authenticateTheUser")
		 	.permitAll()
	    .and()
	    .logout()
	    .permitAll();
 }
}
