package com.sist.hr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="main/main.do")
	public String hello(Model model) {
		
		log.debug("msg", "hello");
		
		// main/main -> prifix(/) /main/main.jsp
		return "main/main";
	}
	
}
