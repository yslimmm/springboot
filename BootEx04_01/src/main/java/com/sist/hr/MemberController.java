package com.sist.hr;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.hr.domain.MemberVO;
import com.sist.hr.service.MemberSvc;

@Controller
public class MemberController {

	private Logger  log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberSvc memberSvc;
	
	@RequestMapping("member/input.do")
	public String member_input() {
		log.info("================");
		log.info("=member_input=");
		log.info("================");
		return "member/input";
	}
	
	@RequestMapping(value="member/output.do",method=RequestMethod.GET)
	public String member_output(HttpServletRequest req,Model model ) {
		String name = req.getParameter("name");
		log.info("================");
		log.info("=member_output=");
		log.info("=name="+name);
		log.info("================");		
		model.addAttribute("name", name+" 반갑습니다.");
		
		return "member/input";
	}
	
	@RequestMapping(value="member/output.do",method=RequestMethod.POST)
	public ModelAndView member_outputPost( MemberVO vo,ModelAndView model ) {
		//String name = req.getParameter("name");
		log.info("================");
		log.info("=member_output=");
		log.info("=vo="+vo);
		log.info("================");	
		model.addObject("name", vo);
		model.setViewName("member/input");
		
		return model;
	}	
	
	@RequestMapping(value="member/do_selectOne.do",method=RequestMethod.POST)
	public ModelAndView member_selectOne( MemberVO vo,ModelAndView model ) {
		log.info("1================");
		log.info("=member_selectOne=");
		log.info("=vo="+vo);
		log.info("1================");
		
	   model.addObject("name", memberSvc.do_selectOne(vo));
	   model.setViewName("member/input");
	   return model;
	}
}
