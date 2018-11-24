package com.sist.hr;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BootEx0402Application {
    static Logger log = LoggerFactory.getLogger(BootEx0402Application.class); 
	public static void main(String[] args) {
		//SpringApplication.run(BootEx0402Application.class, args);
		SpringApplication app= new SpringApplication(BootEx0402Application.class);
		log.info("********************************************");
		log.info("*Hello Boot*");
		log.info("********************************************");
		
		app.setBanner(new Banner() {
			
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.print("\n\n\t 나만의 멋진 배너.\n\n");
				
			}
		});
		
		app.run(args);
	}
}
