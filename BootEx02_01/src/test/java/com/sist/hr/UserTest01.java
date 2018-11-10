package com.sist.hr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * 1 method의 접근 지정자는 public
 * 2.@Test
 * 3. Test에는 return형이 없다.
 * 
 * @author sist1
 *
 */
public class UserTest01 {

	Logger  log = Logger.getLogger(this.getClass());
	
	@Test
	public void count01() {
		log.debug("count01");
		assertThat("1", is("1"));
	}
	
	@Test
	@Ignore	// test 스킵
	public void addAndGet() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");

		User user=new User("yslim93","임예슬","1224");
	
		//Spring은 SingleTon으로 객체를 생성한다.
		UserDao dao=context.getBean("userDao", UserDao.class);
		UserDao dao01=context.getBean("userDao", UserDao.class);
		
		log.debug("=============================");
		log.debug("=dao="+dao);
		log.debug("=dao01="+dao01);
		log.debug("=============================");
		
		
		//--------------------------------
		//단건 삭제
		//--------------------------------	
		int result = dao.del(user.getU_id());
		if(result>0) {
			log.debug("=============================");
			log.debug("=del result="+result);
			log.debug("=============================");
		}
		
		//--------------------------------
		//단건 등록
		//--------------------------------			
		dao.add(user);
		
		//--------------------------------
		//단건 조회
		//--------------------------------				
		User inUser = dao.get(user.getU_id());
		
		//--------------------------------
		//비교
		//--------------------------------
		assertThat(user.getU_id(), is(inUser.getU_id()));
	}
	
	@Test
	@Ignore
	public void count() throws SQLException, ClassNotFoundException {
		ApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");
		
		UserDao userDao = context.getBean("userDao", UserDao.class);
		
		User user01 = new User("ys01_119", "SpringBoot", "spring1234");
		User user02 = new User("ys02_119", "SpringBoot", "spring1234");
		User user03 = new User("ys03_119", "SpringBoot", "spring1234");
		
		userDao.del(user01.getU_id());
		userDao.del(user02.getU_id());
		userDao.del(user03.getU_id());
		
		userDao.add(user01);
		assertThat(userDao.getCount("119"), is(1));		// 1건 나와야지 성공
		
		userDao.add(user02);
		assertThat(userDao.getCount("119"), is(2));		// 2건 나와야지 성공
		
		userDao.add(user03);
		assertThat(userDao.getCount("119"), is(3));		// 3건 나와야지 성공
		
	}
	
	// 메소드 예외 처리
	// @Test(timeout=)은 테스트 하기까지의 시간을 테스트할 수 있다.
	@Test(expected=EmptyResultDataAccessException.class)
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
