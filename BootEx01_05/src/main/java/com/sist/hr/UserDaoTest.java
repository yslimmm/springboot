package com.sist.hr;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class UserDaoTest {

	static Logger log = Logger.getLogger(UserDaoTest.class);
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		User user = new User("yslim_119", "임예슬", "1234");
		ConnectionMaker connectionMaker = new NConnectionMaker();
		UserDao dao = new UserDao(connectionMaker);
		
		// 단건 삭제
		int result = dao.del("yslim_119");

		if(result > 0) {
			log.debug("delete result : " + result);

			// 단건 등록
			dao.add(user);
		}
		
		// 단건 조회
		User inUser = dao.get(user.getU_id());
		
		// 비교조회
		if(user.getU_id().equals(inUser.getU_id()) &&
		   user.getName().equals(inUser.getName()) &&
		   user.getPassword().equals(inUser.getPassword())) {
			
			log.debug("등록 성공");
		} else {
			log.debug("등록 실패");
		}
	}
}