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

/**
 * 1 method의 접근 지정자는 public
 * 2.@Test
 * 
 * @author sist1
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserTest03 {

	Logger  LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	private ApplicationContext context;
	
	private UserDao dao;
	
	private User user01;
	private User user02;
	private User user03;
	
	@Before
	public void setup() {	
		user01 = new User("j01_115","Spring Boot","spring1234",Level.BASIC,1,0,"jamesol01@paran.com","2018-11-17");
		user02 = new User("j02_115","Spring Boot","spring1234",Level.SILVER,51,10,"jamesol02@paran.com","2018-11-17");
		user03 = new User("j03_115","Spring Boot","spring1234",Level.GOLD,51,31,"jamesol03@paran.com","2018-11-17");
		
		dao = (UserDao) context.getBean("userDao");
		LOG.debug("setup");
		LOG.debug("**context**:"+context);
		LOG.debug("this:"+this);
	}
	
	@Test
	public void update() throws ClassNotFoundException, SQLException {
		//전체 삭제
		//단건 입력
		//수정data 생성
		//수정처리
		//비교
		//----------------------------------
		//1.전체 삭제
		//----------------------------------
		dao.delAll();
		
		//----------------------------------
		//2.단건 입력
		//----------------------------------	
		dao.add(user01);
		
		
		//----------------------------------
		//3.수정data 생성,수정
		//----------------------------------		
		User userVS = new User("j01_115","Spring Boot_9","spring1234_9",Level.BASIC,9,99,"jamesol019@paran.com","2018-11-17");
		int flag = dao.update(userVS);
		LOG.debug("flag: "+flag);

		//----------------------------------
		//4.수정data 조회
		//----------------------------------			
		User userM = dao.get(user01.getU_id());

		//----------------------------------
		//5.Data비교
		//----------------------------------
		this.checkSampleUser(userVS, userM);
	}
	
	
	@Test
	@Ignore
	public void getAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		List<User> list = dao.getAll();
		assertThat(list.size(), is(0));
		LOG.debug("list:"+list);
		
		
	}
	
	@Test
	@Ignore
	public void delAll() throws SQLException, ClassNotFoundException {
		dao.delAll();
		assertThat(dao.getCount(""),is(0));
	}
	
	
	//메소드 예외 처리
	@Test(expected=NullPointerException.class)
	@Ignore
	public void getUserFailure()throws SQLException, ClassNotFoundException,EmptyResultDataAccessException{

		 
		dao.del(user01.getU_id());
		dao.del(user02.getU_id());
		dao.del(user03.getU_id());
		 
		dao.delAll();
		assertThat(dao.getCount(""), is(0));
		 
		dao.get("unknown_id");
		 
	}
	
	@Test
	@Ignore
	public void count()throws SQLException, ClassNotFoundException{

		 
		 dao.delAll();
		 
		 dao.add(user01);
		 assertThat(dao.getCount("115"), is(1));
		 
		 dao.add(user02);
		 assertThat(dao.getCount("115"), is(2));
		 
		 dao.add(user03);
		 assertThat(dao.getCount("115"), is(3));		 
		
	}
	
	@Test
	@Ignore
	public void addAndGet()throws SQLException, ClassNotFoundException{



	//Spring은 SingleTon으로 객체를 생성한다.
	//1.객체 2개 생성확인
	LOG.debug("=============================");
	LOG.debug("=dao="+dao);
	LOG.debug("=============================");
	
	
	//--------------------------------
	//단건 삭제
	//--------------------------------	
	int result = dao.del(user01.getU_id());
	if(result>0) {
		LOG.debug("=============================");
		LOG.debug("=del result="+result);
		LOG.debug("=============================");
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

	checkSampleUser(user01,inUser);
	
	}
	
	private void checkSampleUser(User user1,User user2) {
		assertThat(user1.getU_id(),is(user2.getU_id()));
		assertThat(user1.getName(),is(user2.getName()));
		assertThat(user1.getPassword(),is(user2.getPassword()));
		
		assertThat(user1.gethLevel(),is(user2.gethLevel()));
		assertThat(user1.getLogin(),is(user2.getLogin()));
		assertThat(user1.getRecommend(),is(user2.getRecommend()));
		assertThat(user1.getEmail(),is(user2.getEmail()));
		
	}
	
	
	@Test
	@Ignore
	public void count01() {
		LOG.debug("count01");
		assertThat("1", is("1"));
	}
}
