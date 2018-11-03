package com.sist.hr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	// 어플리케이션 컨텍스트 또는 빈 팩토리라고 알려주는 것
public class DaoFactory {
	
	/**
	 * DaoFactory
	 * @return UserDao
	 */
	@Bean		// 빈 등록
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(getConnectionMaker());
		return userDao;
	}

	public ConnectionMaker getConnectionMaker() {
		return new NConnectionMaker();
	}
}
