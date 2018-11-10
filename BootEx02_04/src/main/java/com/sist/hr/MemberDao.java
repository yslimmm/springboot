package com.sist.hr;

import org.apache.log4j.Logger;

public class MemberDao implements CommonDao {

	Logger log = Logger.getLogger(MemberDao.class);
	
	public void do_save() {
		log.debug("====do_save====");
	}

	public void do_update() {
		log.debug("====do_update====");
	}

	public void do_delete() {
		log.debug("====do_delete====");
	}

	public void do_retrieve() {
		log.debug("====do_retrive====");
	}

}
