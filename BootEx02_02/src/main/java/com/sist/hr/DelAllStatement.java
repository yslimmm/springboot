package com.sist.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DelAllStatement implements StatementStrategy {

	Logger log = Logger.getLogger(DelAllStatement.class);
	
	public PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("DELETE FROM users");
		return ps;
	}
}
