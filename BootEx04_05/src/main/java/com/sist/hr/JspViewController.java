package com.sist.hr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspViewController {

	private final Logger log = LoggerFactory.getLogger(JspViewController.class);
	
	@RequestMapping(value="/")
	public String root() {
		///WEB-INF/jsp/ +viewtest+ .jsp
		log.info("+++++++++++++++++++++++++++++++");
		log.info("+root+");
		log.info("+++++++++++++++++++++++++++++++");
		return "viewtest";  
	}
}
