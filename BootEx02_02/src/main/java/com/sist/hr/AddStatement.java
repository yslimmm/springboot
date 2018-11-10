package com.sist.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class AddStatement implements StatementStrategy {

	Logger log = Logger.getLogger(AddStatement.class);
	User user;
	
	public AddStatement() {}
	public AddStatement(User user) {
		this.user = user;
	}
	
	public PreparedStatement makeStatement(Connection c) throws SQLException {
		// query
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO users(u_id, name, password) VALUES(?, ?, ?)");
		log.debug("sql : " + sb.toString());
	
		// 실행
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1, user.getU_id());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		return ps;
	}

}
