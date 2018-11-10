package com.sist.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	public abstract PreparedStatement makeStatement(Connection c) throws SQLException;
}
