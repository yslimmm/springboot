package com.sist.hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoJdbc implements UserDao {
//	 * IP:211.238.142.102
//	 * post:1521
//	 * SID:orcl
//	 * sist/1224
	
	private static Logger log = Logger.getLogger(UserDaoJdbc.class);
	private RowMapper<User> userMapper = 
			new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setU_id(rs.getString("u_id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					
					user.sethLevel(Level.valueOf(rs.getInt("h_level")));
					user.setLogin(rs.getInt("login"));
					user.setRecommend(rs.getInt("recommend"));
					user.setEmail(rs.getString("email"));
					user.setRegDt(rs.getString("reg_dt"));
					return user;
				}
    };
	
	//전체 삭제,등록
    private JdbcTemplate jdbcTemplate;
    


	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	public UserDaoJdbc() {
		
	}

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#getCount(java.lang.String)
	 */
	public int getCount(String user_id)throws ClassNotFoundException,SQLException{
		
		
		int cnt = 0;
		
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder sb=new StringBuilder();		
		sb.append(" SELECT            \n");
		sb.append("     count(*) cnt  \n");
		sb.append(" FROM users        \n");
		sb.append(" WHERE u_id LIKE ? \n");
		log.debug("sql:\n"+sb.toString());
		//--------------------------------------------		
		//실행
		//--------------------------------------------
		log.debug("param:\n"+user_id);
		return this.jdbcTemplate.queryForObject(sb.toString()
				,new Object[] {"%"+user_id+"%"}
		        ,Integer.class);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#delAll()
	 */
	public void delAll()throws SQLException, ClassNotFoundException{
		jdbcTemplate.update("DELETE FROM users");
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#del(java.lang.String)
	 */
	public int del(String user_id)throws ClassNotFoundException,SQLException{
		
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder sb=new StringBuilder();
		sb.append(" DELETE FROM users WHERE u_id=? \n");
		log.debug("sql:\n"+sb.toString());
		log.debug("param:\n"+user_id);
		//--------------------------------------------		
		//실행
		//--------------------------------------------		
		Object[] args = {user_id};
		
		return this.jdbcTemplate.update(sb.toString(), args);
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#getAll()
	 */
	public List<User> getAll()throws ClassNotFoundException,SQLException{
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT                                     \n");
		sb.append("     u_id,                                  \n");
		sb.append("     name,                                  \n");
		sb.append("     password,                              \n");
		sb.append("     h_level,                               \n");
		sb.append("     login,                                 \n");
		sb.append("     recommend,                             \n");
		sb.append("     email,                                 \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') as reg_dt \n");
		sb.append(" FROM users                                 \n");
		sb.append(" ORDER BY  u_id                             \n"); 
		log.debug("sql:\n"+sb.toString());
		return this.jdbcTemplate.query(sb.toString()
				,  userMapper);
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#get(java.lang.String)
	 */
	public User get(String user_id)
			throws ClassNotFoundException,SQLException,EmptyResultDataAccessException{
		//--------------------------------------------		
		//query
		//--------------------------------------------
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT                                     \n");
		sb.append("     u_id,                                  \n");
		sb.append("     name,                                  \n");
		sb.append("     password,                              \n");
		sb.append("     h_level,                               \n");
		sb.append("     login,                                 \n");
		sb.append("     recommend,                             \n");
		sb.append("     email,                                 \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD') as reg_dt \n");
		sb.append(" FROM users                                 \n");
		sb.append(" WHERE u_id = ?   \n");
		log.debug("sql:\n"+sb.toString());
		
		return this.jdbcTemplate.queryForObject(sb.toString()
				, new Object[] {user_id}
		        , userMapper
		      );
		
	}

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDao#add(com.sist.hr.User)
	 */
	public void add(final User user)throws ClassNotFoundException,SQLException{
		StringBuilder sb=new StringBuilder();
		sb.append(" INSERT INTO users ( \n");
		sb.append("     u_id,           \n");
		sb.append("     name,           \n");
		sb.append("     password,       \n");
		sb.append("     h_level,        \n");
		sb.append("     login,          \n");
		sb.append("     recommend,      \n");
		sb.append("     email,          \n");
		sb.append("     reg_dt          \n");
		sb.append(" ) VALUES (          \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     ?,              \n");
		sb.append("     sysdate         \n");
		sb.append(" )                   \n");
		log.debug("sql:\n"+sb.toString());
		Object[] args = {user.getU_id()
				,user.getName()
				,user.getPassword()
				,user.gethLevel().intValue()
				,user.getLogin()
				,user.getRecommend()
				,user.getEmail()};
		
		jdbcTemplate.update(sb.toString(),args );

	}

	public int update(User user) throws SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append(" UPDATE users              \n");
		sb.append(" SET  name      = ?        \n");
		sb.append("     ,password  = ?        \n");
		sb.append("     ,h_level   = ?        \n");
		sb.append("     ,login     = ?        \n");
		sb.append("     ,recommend = ?        \n");
		sb.append("     ,email     = ?        \n");
		sb.append("     ,reg_dt    = sysdate  \n");
		sb.append(" WHERE u_id     = ?        \n");
		
		log.debug("sql:\n"+sb.toString());
		Object[] args = {user.getName()
				,user.getPassword()
				,user.gethLevel().intValue()
				,user.getLogin()
				,user.getRecommend()
				,user.getEmail()
				,user.getU_id()
				};
		log.debug("param:\n"+user);
		jdbcTemplate.update(sb.toString(),args );		
		return 0;
	}

}
