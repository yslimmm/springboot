package com.sist.hr;

import org.apache.log4j.Logger;

public class Hello {

	static Logger log = Logger.getLogger(Hello.class);
	
	public static void main(String[] args) {
		System.out.println("Hello Spring boot.");
		log.debug("Log4j =============");
	}

}
