package com.sist.hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class UserDao {

//	 * 오라클 연결정보
//	 * ip 	: 211.238.142.102
//	 * port	: 1521
//	 * sid 	: orcl
//	 * sist/1224
	
	private static Logger log = Logger.getLogger(UserDao.class);
	private ConnectionMaker connectionMaker;
	
	public UserDao() { }
	
	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public User get(String user_id) throws ClassNotFoundException, SQLException {
		// DB 연결
		Connection c = connectionMaker.makeConnection();
		
		// query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u_id, name, password FROM users WHERE u_id = ?");
		log.debug("sql : " + sb.toString());
		
		// 실행
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, user_id);
		
		// 조회
		User user = null;
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			user = new User();
			
			user.setU_id(rs.getString("u_id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		log.debug("user : " + user);
		
		ps.close();
		c.close();
		
		return user;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException {
		// DB 연결
		Connection c = connectionMaker.makeConnection();
		
		// query
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO users(u_id, name, password) VALUES(?, ?, ?)");
		log.debug("sql : " + sb.toString());

		// 실행
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, user.getU_id());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		int flag = ps.executeUpdate();
		log.debug("flag : " + flag);
		
		ps.close();
		c.close();
		
	}

	public int del(String user_id) throws ClassNotFoundException, SQLException {
		// DB 연결
		Connection c = connectionMaker.makeConnection();
		
		// query
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM users WHERE u_id = ?");
		log.debug("sql : " + sb.toString());
		
		// 실행
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, user_id);
		
		int flag = ps.executeUpdate();
		log.debug("flag : " + flag);
		
		ps.close();
		c.close();
		
		return flag;
	}
}