package com.sist.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

//	 * 오라클 연결정보
//	 * ip 	: 211.238.142.102
//	 * port	: 1521
//	 * sid 	: orcl
//	 * sist/1224
	
	private Logger log = Logger.getLogger(UserDao.class);
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	
	public UserDao() { }
	
	/**
	 * xml에 있는 dataSource 갖고오기
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}
	
	// 컨텍스트
	// 전략패턴
	public void jdbcContextWithStatmentStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException {
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			// db 연결
			c = dataSource.getConnection();
			
			// 실행
			ps = stmt.makeStatement(c);

			int flag = ps.executeUpdate();
			log.debug("flag:\n"+flag);
			
		} finally {
			if(ps != null) {
				try { ps.close(); } catch (Exception e) { }
			}
			
			if(c != null) {
				try { c.close(); } catch (Exception e) { }
			}
		}
	}
	
	public void delAll() throws SQLException, ClassNotFoundException {

		jdbcContext.workWithStatmentStrategy(new StatementStrategy() {
			public PreparedStatement makeStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("DELETE FROM users");
				return ps;
			}
		});
	}
	
	
	public int getCount(String user_id) throws ClassNotFoundException, SQLException {
		int cnt = 0;
		
		// db연결
		Connection c = dataSource.getConnection();
		
		// query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(*) cnt FROM users WHERE u_id LIKE ?");
		log.debug("sql : " + sb.toString());
		
		// 실행
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, "%" + user_id + "%");
		
		// 조회
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		log.debug("cnt : " + cnt);
		
		rs.close();
		ps.close();
		c.close();
		
		return cnt;
	}
	
	public User get(String user_id) throws ClassNotFoundException, SQLException {
		// DB 연결
		Connection c = dataSource.getConnection();
		
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
		
		rs.close();
		ps.close();
		c.close();
		
		// 예외처리
		if(null == user) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return user;
	}
	
	public void add(final User user) throws ClassNotFoundException, SQLException {

		jdbcContext.workWithStatmentStrategy(new StatementStrategy() {

			public PreparedStatement makeStatement(Connection c) throws SQLException {
				// query
				StringBuilder sb = new StringBuilder();
				sb.append("INSERT INTO users(u_id, name, password) VALUES(?, ?, ?)");
				log.debug("sql : " + sb.toString());
			
				// 실행
				PreparedStatement ps = c.prepareStatement(sb.toString());
				
				// 로컬변수에 직접 접근이 불가능하므로 add매개변수에 final로 만들어주었다.
				ps.setString(1, user.getU_id());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				
				return ps;
			}
		});
	}

	public int del(String user_id) throws ClassNotFoundException, SQLException {
		// DB 연결
		Connection c = dataSource.getConnection();
		
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