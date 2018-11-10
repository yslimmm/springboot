package com.sist.hr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)	// 스프링이 지원하는 테스트 지원 어노테이션
@ContextConfiguration(locations="/applicationContext.xml")
public class UserTest03 {

	Logger  log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ApplicationContext context;
	
	private UserDao dao;
	
	private User user01;
	private User user02;
	private User user03;
	
	@Before
	public void setup() {
		user01 = new User("ys01_119", "SpringBoot", "spring1234");
		user02 = new User("ys02_119", "SpringBoot", "spring1234");
		user03 = new User("ys03_119", "SpringBoot", "spring1234");
		
		dao = context.getBean("userDao", UserDao.class);
		log.debug("====setup====");
		log.debug("context : 객체가 잘 만들어져있니??? -> : " + context);
		log.debug("context은 싱글턴이구나~");
		log.debug("userDao" + dao);
		
		log.debug("싱글턴일까? (this) :" + this);
		log.debug("context는 공유가 되지만 this는 다르다.");
	}
	
	@Test
	@Ignore
	public void delAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		
		assertThat(dao.getCount(""), is(0)); 	// 다 지워졌을 테니 0건일 것이다.
	}
	
	@Test
	public void getAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		
		List<User> list = dao.getAll();
		assertThat(list.size(), is(0));
		log.debug("list : " + list);
	}
	
	@Test
	@Ignore
	public void count() throws SQLException, ClassNotFoundException {
		
		dao.del(user01.getU_id());
		dao.del(user02.getU_id());
		dao.del(user03.getU_id());
		
		dao.add(user01);
		assertThat(dao.getCount("119"), is(1));		// 1건 나와야지 성공
		
		dao.add(user02);
		assertThat(dao.getCount("119"), is(2));		// 2건 나와야지 성공
		
		dao.add(user03);
		assertThat(dao.getCount("119"), is(3));		// 3건 나와야지 성공
		
	}
	
	
	@Test
	@Ignore
	public void addAndGet() throws SQLException, ClassNotFoundException {
		log.debug("=============================");
		log.debug("=dao=" + dao);
		log.debug("=============================");
		
		
		//--------------------------------
		//단건 삭제
		//--------------------------------	
		int result = dao.del(user01.getU_id());
		if(result>0) {
			log.debug("=============================");
			log.debug("=del result="+result);
			log.debug("=============================");
		}
		
		//--------------------------------
		//단건 등록
		//--------------------------------			
		dao.add(user01);
		
		//--------------------------------
		//단건 조회
		//--------------------------------				
		User inUser = dao.get(user01.getU_id());
		
		//--------------------------------
		//비교
		//--------------------------------
		assertThat(user01.getU_id(), is(inUser.getU_id()));
	}
	
	// 메소드 예외 처리
	// @Test(timeout=)은 테스트 하기까지의 시간을 테스트할 수 있다.
	@Test(expected=EmptyResultDataAccessException.class)
	@Ignore
	public void getUserFailure() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		
		UserDao userDao = context.getBean("userDao", UserDao.class);
		
		User user01 = new User("ys01_119", "SpringBoot", "spring1234");
		User user02 = new User("ys02_119", "SpringBoot", "spring1234");
		User user03 = new User("ys03_119", "SpringBoot", "spring1234");
		
		userDao.del(user01.getU_id());
		userDao.del(user02.getU_id());
		userDao.del(user03.getU_id());
		
		assertThat(userDao.getCount("119"), is(0));		// 다 지웠으니 0건 나와야지 성공
		
		userDao.get("unknown_id");
	}
}
