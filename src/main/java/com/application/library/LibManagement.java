package com.application.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication //is equivalent to using @Configuration, @EnableAutoConfiguration and @ComponentScan
@EnableJpaRepositories 
public class LibManagement  extends SpringBootServletInitializer  
{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LibManagement.class);
    }
	
    public static void main( String[] args )
    {
        SpringApplication.run(LibManagement.class, args);
    }
}
