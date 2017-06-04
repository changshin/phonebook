package com.chang.ng.phone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


// without SpringBootServletInitializer, mvn tomcat7:run does not work
@SpringBootApplication
// N/A @PropertySource("classpath:application.properties")
public class StartApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
}
