package com.sist.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JdbcContext {
	
	Logger log = Logger.getLogger(JdbcContext.class);
	
	private DataSource dataSource;
	
	/**
	 * xml에 있는 dataSource 갖고오기
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// 컨텍스트
	public void workWithStatmentStrategy(StatementStrategy stmt) throws SQLException, ClassNotFoundException {
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
}
