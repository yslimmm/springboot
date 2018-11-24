package com.sist.hr.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.hr.domain.Greeting;

@RestController
public class GreetingController {
    private final Logger  log = LoggerFactory.getLogger(this.getClass());
	private static final String template = "Hello, %s!";
	private final AtomicLong counter=new AtomicLong();
	//http://localhost:8080/greeting
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name",defaultValue="World") 
	                           String name) {
		log.info("+++++++++++++++++++++++++++++++++");
		log.info("name="+name);
		log.info("+++++++++++++++++++++++++++++++++");
		
		return new Greeting(counter.incrementAndGet(),String.format(template, name));
	}
}
