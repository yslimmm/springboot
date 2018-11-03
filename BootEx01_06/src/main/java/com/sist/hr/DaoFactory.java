package com.sist.hr;

public class DaoFactory {
	
	/**
	 * DaoFactory
	 * @return UserDao
	 */
	public UserDao userDao() {
		return new UserDao(getConnectionMaker());
	}
	
//	/**
//	 * DaoFactory
//	 * @return UserDao
//	 */
//	public BoardDao userDao() {
//		ConnectionMaker connectionMaker = new NConnectionMaker();
//		UserDao dao = new UserDao(connectionMaker);
//		
//		return dao;
//	}
	
	public ConnectionMaker getConnectionMaker() {
		return new NConnectionMaker();
	}
}
