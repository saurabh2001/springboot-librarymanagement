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
	
	public MyWebSecurityConfig() {
        super();
    }
	
	/* Following code is working for in memory authentication
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("mkyong").password("{noop}123").roles("USER");
	  auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("dba").password("{noop}123").roles("DBA");
	  
	}*/
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
		//.antMatchers("/homePage").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
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
	
	/*@Override @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
		auth
		.inMemoryAuthentication()
		.passwordEncoder(bCryptPasswordEncoder)
			.withUser("user").password("{noop}password").roles("USER");
    }*/
	
	/*protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().permitAll();        
	}*/
	
	

	
	
}
