package com.sist.hr.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sist.hr.domain.MemberVO;

@Repository
public class MemberDaoImple implements MemberDao {
	
	private Logger  log = LoggerFactory.getLogger(MemberDaoImple.class);
	
	/* (non-Javadoc)
	 * @see com.sist.hr.dao.MemberDao#do_selectOne(com.sist.hr.domain.MemberVO)
	 */
	@Override
	public MemberVO do_selectOne(MemberVO vo) {
		log.info("3=====================");
		log.info("=@Repository=====");
		log.info("=vo====="+vo);
		log.info("3=====================");
		
		vo.setName(vo.getName()+" 반갑습니다.");
		return vo;
	}
}
