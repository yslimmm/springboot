package com.sist.hr;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserTest02 {

	Logger  log = Logger.getLogger(this.getClass());
	
	private UserDao dao;
	
	// 선행 메소드: @Test가 수행되기 전 1회 수행.
	@Before
	public void setUp() {
		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		log.debug("=====1. setUp=====");
	}
	
	// @Test 순서는 임의다...
	@Test
	public void addAndGet() {
		log.debug("=====addAndGet=====");
	}
	
	@Test
	public void count() {
		log.debug("=====count=====");
	}
	
	@After
	public void tearDown() {
		log.debug("=====4. tearDown=====");
	}
}
