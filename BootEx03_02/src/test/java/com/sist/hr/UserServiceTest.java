package com.sist.hr;

import static com.sist.hr.UserService.MIN_GOLD_RECOMMAND_COUNT;
import static com.sist.hr.UserService.MIN_SILVER_LOGIN_COUNT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserServiceTest {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	private List<User> list;
	
	@Before
	public void setUp() {
		list = Arrays.asList(
				 new User("j01_115","Spring Boot01","spring1234",Level.BASIC,MIN_SILVER_LOGIN_COUNT-1,0,"jamesol01@paran.com","2018-11-17")
				,new User("j02_115","Spring Boot02","spring1234",Level.BASIC,MIN_SILVER_LOGIN_COUNT,10,"jamesol01@paran.com","2018-11-17")
				,new User("j03_115","Spring Boot03","spring1234",Level.SILVER,50,MIN_GOLD_RECOMMAND_COUNT-1,"jamesol01@paran.com","2018-11-17")
				,new User("j04_115","Spring Boot04","spring1234",Level.SILVER,50,MIN_GOLD_RECOMMAND_COUNT,"jamesol01@paran.com","2018-11-17")
				,new User("j05_115","Spring Boot05","spring1234",Level.GOLD,100,100,"jamesol01@paran.com","2018-11-17")
				);
	}
	
	@Test
	public void add() throws ClassNotFoundException, SQLException {
		
		//data삭제
		//Level이 있는 경우(정상)
		//Level이 null인 경우 처리 
		//조회후 비교
		
		//------------------------------------
		//1. 전체 data삭제
		//------------------------------------		
		this.userDao.delAll();
		
		//------------------------------------
		//2. Level이 있는 경우(정상),Level이 null인 경우 처리 
		//------------------------------------	
		User userWithLevel = this.list.get(4);
		User userWithOutLevel = this.list.get(0);
		userWithOutLevel.sethLevel(null);
		
		//------------------------------------
		//3. service add() 메소드 call 
		//------------------------------------	
		this.userService.add(userWithLevel);
		this.userService.add(userWithOutLevel);
		
		//------------------------------------
		//4. 조회 후 비교 
		//------------------------------------	
		User userWithLevelRead = this.userDao.get(userWithLevel.getU_id());
		User userWithOutLevelRead = this.userDao.get(userWithOutLevel.getU_id());
		
		assertThat(userWithLevelRead.gethLevel(),is(userWithLevelRead.gethLevel()));
		assertThat(userWithOutLevelRead.gethLevel(),is(Level.BASIC));
	}
	
	
	
	@Test(expected=RuntimeException.class)
	public void upgradeLevels() 
			throws ClassNotFoundException, SQLException, IllegalAccessException {
		//전체 삭제
		//list data추가
		//등업 수행
		//비교
		
		//-------------------------------------------
		//1.전체 삭제
		//-------------------------------------------		
		this.userDao.delAll();
		
		//-------------------------------------------
		//2.list data추가
		//-------------------------------------------		
		for(User user:list) {
			userDao.add(user);
		}
		
		//-------------------------------------------
		//3.등업 수행
		//-------------------------------------------	
		this.userService.upgradeLevels();
		
		//-------------------------------------------
		//4.비교
		//-------------------------------------------	
		checkLevel(list.get(0),false);
		checkLevel(list.get(1),true);
		checkLevel(list.get(2),false);
		checkLevel(list.get(3),true);  
		checkLevel(list.get(4),false);
		
	}
	
	private void checkLevel(User user, boolean upgraded) throws EmptyResultDataAccessException, ClassNotFoundException, SQLException {
		User userUpdate = userDao.get(user.getU_id());
		if(upgraded) {
			assertThat(userUpdate.gethLevel(), is(user.gethLevel().getNextLevel()));
		}else {
			assertThat(userUpdate.gethLevel(), is(user.gethLevel()));
		}
		
		
		
	}
	
	
	
	
	@Test
	@Ignore
	public void bean() {
		log.debug("this.userService:"+this.userService);
		assertThat(this.userService, is(notNullValue()));
	}
	
}
