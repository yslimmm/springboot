package com.sist.hr;

import org.apache.log4j.Logger;

public class UserDaoTest {

	static Logger log = Logger.getLogger(UserDaoTest.class);
	public static void main(String[] args) {
		
		User user = new User("yslim_119", "임예슬", "1234");
		UserDao dao = new UserDao();
		
		try {
			// 단건 등록
			dao.add(user);
			
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
