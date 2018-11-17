package com.sist.hr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class UserService {
	private Logger log = Logger.getLogger(this.getClass());
	
	private PlatformTransactionManager transactionManager;
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static final int MIN_SILVER_LOGIN_COUNT   = 50;//BASIC->SILVER
	public static final int MIN_GOLD_RECOMMAND_COUNT = 30;//SILVER->GOLD
	private UserDao userDao;


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 사용자 추가: 최초 추가 처리 
	 * @param user
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void add(User user) throws ClassNotFoundException, SQLException {
		if(null == user.gethLevel())user.sethLevel(Level.BASIC);
		userDao.add(user);
	}
	
	
	/**
	 * 등업
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 */
	public void upgradeLevels() throws	ClassNotFoundException, SQLException, IllegalAccessException,RuntimeException {
		//전체 조회
		//등업 대상 추출
		//등업 대상에 해당되면 등업
		//---------------------------------------
		//1.전체 조회
		//---------------------------------------	
		//TransactionStatus status = 
	//this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		 TransactionSynchronizationManager.initSynchronization();
		 Connection c = DataSourceUtils.getConnection(dataSource);
		
		try {
			List<User> list = userDao.getAll();
			
			//---------------------------------------
			//2.등업 대상 추출
			//---------------------------------------
			int upCnt = 0;
			for(User user:list) {
				//Basic  && loginCnt>=50
				//Silver && recommand>=30
				//gold
				
				//임시 Exception발생
				if(user.getU_id().equals("j04_115")) {
					throw new RuntimeException("사용자 익셉션:"+user.getU_id()); 
				}
				
				if(isCanUpgradeLevel(user)) {
					upCnt++;
					upgradeLevel(user);
				}
			}
			//this.transactionManager.commit(status);
			c.commit();
			
		}catch(RuntimeException e) {
			log.debug("===============================");
			log.debug("RuntimeException="+e.getMessage());
			log.debug("===============================");
			//this.transactionManager.rollback(status);
			c.rollback();
			throw e;
		}finally {
			//스프링 유팅리티 메소드를 통해 커넥션을 안전하게 종료.
			DataSourceUtils.releaseConnection(c, dataSource);
			//동기화 메소드 종료
			TransactionSynchronizationManager.unbindResource(dataSource);
			TransactionSynchronizationManager.clearSynchronization();
		}
		
		

		
	}
	
	/**
	 * 
	 * @param user
	 * @throws SQLException 
	 */
	public void upgradeLevel(User user) throws SQLException {
		user.upgradeLevel();
		userDao.update(user);
	}
	
	
	public boolean isCanUpgradeLevel(User user) throws IllegalAccessException {
		Level curLevel = user.gethLevel();
		
		switch(curLevel) {
			case BASIC:  return (user.getLogin()>=MIN_SILVER_LOGIN_COUNT);
			case SILVER: return (user.getRecommend()>=MIN_GOLD_RECOMMAND_COUNT);
			case GOLD:   return false;
			default: throw new IllegalAccessException("Unknown Level:"+curLevel);
		}
	}
	
}












