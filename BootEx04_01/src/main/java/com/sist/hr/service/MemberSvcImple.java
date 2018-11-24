package com.sist.hr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.hr.dao.MemberDao;
import com.sist.hr.dao.MemberDaoImple;
import com.sist.hr.domain.MemberVO;

@Service
public class MemberSvcImple implements MemberSvc {
	
	private Logger  log = LoggerFactory.getLogger(MemberSvcImple.class);
	
	@Autowired
	private MemberDao memberDao;
	
	/* (non-Javadoc)
	 * @see com.sist.hr.service.MemberSvc#do_selectOne(com.sist.hr.domain.MemberVO)
	 */
	@Override
	public MemberVO do_selectOne(MemberVO vo) {
		log.info("2=============================");
		log.info("=@Service");
		log.info("2=============================");
		return memberDao.do_selectOne(vo);
	}
}
