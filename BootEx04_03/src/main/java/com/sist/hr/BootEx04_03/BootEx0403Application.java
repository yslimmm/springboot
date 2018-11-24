package com.sist.hr.BootEx04_03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootEx0403Application 
     implements CommandLineRunner,ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(BootEx0403Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BootEx0403Application.class, args);
		
		
	}

	@Bean
	public String info() {
		return "charator ";
	}
	
	@Autowired
	String info;
	
	@Override
	// > mvnw spring-boot:run -Drun.arguments="snow,rain,sun"
	public void run(String... args) throws Exception {
		log.info(">>>> CommandLineRunner 구현체");
		for(String arg:args) {
			log.info("arg:"+arg);
		}
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info(">>>> ApplicationArguments ");
		log.info("info "+info);
		args.getNonOptionArgs().forEach(file->log.info(file));
		
	}
}
