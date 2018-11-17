package com.sist.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {

//	 * 오라클 연결정보
//	 * ip 	: 211.238.142.102
//	 * port	: 1521
//	 * sid 	: orcl
//	 * sist/1224
	
	private Logger log = Logger.getLogger(UserDao.class);
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private RowMapper<User> userMapper = new RowMapper<User>() {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setU_id(rs.getString("u_id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};
		
	public UserDao() { }
	
	/**
	 * xml에 있는 dataSource 갖고오기
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}
	
	public void delAll() throws SQLException, ClassNotFoundException {

		jdbcTemplate.update("DELETE FROM users");

		// 같은 목적의 코드
//		jdbcTemplate.update(new PreparedStatementCreator() {
//
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				return con.prepareStatement("DELETE FROM users");
//			}
//		});
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
		// query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u_id, name, password FROM users WHERE u_id = ?");
		log.debug("sql : " + sb.toString());
		
		return this.jdbcTemplate.queryForObject(sb.toString()
												, new Object[] {user_id}
												, userMapper);
	}
	
	public void add(final User user) throws ClassNotFoundException, SQLException {

		jdbcTemplate.update("INSERT INTO users(u_id, name, password) VALUES(?, ?, ?)", user.getU_id(), user.getName(), user.getPassword());
		
	}

	public List<User> getAll() throws ClassNotFoundException, SQLException {
		// query
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u_id, name, password FROM users");
		log.debug("sql : " + sb.toString());
		
		return this.jdbcTemplate.query(sb.toString()
										, userMapper);
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