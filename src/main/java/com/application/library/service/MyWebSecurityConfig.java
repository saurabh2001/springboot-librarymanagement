package com.application.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//http://www.devglan.com/spring-security/spring-boot-security-password-encoding-bcrypt-encoder
@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public MyWebSecurityConfig() {
        super();
    }
	
	@Override @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
		
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/resources/**", "/signup", "/aboutus", "/loginPage").permitAll()
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
				.formLogin().loginPage("/loginPage").failureUrl("/loginPage?error")
				//.defaultSuccessUrl("/listbooks")
				.usernameParameter("username").passwordParameter("password")		
				.successHandler(new CustomAuthenticationSuccessHandler())
			.and()
				.logout().logoutSuccessUrl("/loginPage?logout"); 
		
	}
	
	/* Following is the code for in memory authentication
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("sau").password("{noop}123").roles("USER");
	  auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("dba").password("{noop}123").roles("DBA");
	  
	}
	
	@Override @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
		auth
		.inMemoryAuthentication()
		.passwordEncoder(bCryptPasswordEncoder)
			.withUser("user").password("{noop}password").roles("USER");
    }*/
	
	
	/*
	 * Following code can be used to enable  all request
	 * protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().permitAll();        
	}*/
	
	

	
	
}
