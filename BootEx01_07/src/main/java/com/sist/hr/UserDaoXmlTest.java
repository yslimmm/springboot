package com.sist.hr;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoXmlTest {

	static Logger log = Logger.getLogger(UserDaoXmlTest.class);
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		// 어플리케이션 컨텍스트 생성, 부트는 클래스 객체 주입으로하지만 스프링은 xml로 주입을 하기 때문에 xml파일을 읽도록한다.
		ApplicationContext context = new GenericXmlApplicationContext("/applicaionContext.xml");
		
		User user = new User("yslim_119", "임예슬", "1234");
		
		// Spring은 SingleTon으로 객체를 생성한다.
		// 1. 객체 2개 생성확인
		// 2. 객체 1개 생성확인
		UserDao dao = context.getBean("userDao"/*DaoFactory에 등록된 즉 어플리케이션 컨텍스트에 등록되어진 빈 이름(메소드이름)*/, UserDao.class);
		UserDao dao2 = context.getBean("userDao"/*DaoFactory에 등록된 즉 어플리케이션 컨텍스트에 등록되어진 빈 이름(메소드이름)*/, UserDao.class);
		
		log.debug("===================");
		log.debug("dao : " + dao);
		log.debug("dao2 : " + dao2);
		log.debug("===================");
		
		// 단건 삭제
		int result = dao.del("yslim_119");

		if(result > 0) {
			log.debug("delete result : " + result);

			// 단건 등록
			dao.add(user);
		}
		
		// 단건 조회
		User inUser = dao.get(user.getU_id());
		
		// 비교조회
		if(user.getU_id().equals(inUser.getU_id()) &&
		   user.getName().equals(inUser.getName()) &&
		   user.getPassword().equals(inUser.getPassword())) {
			
			log.debug("등록 성공");
		} else {
			log.debug("등록 실패");
		}
	}
}