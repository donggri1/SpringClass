package org.doit.ik.controller;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.persistence.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/joinus/*")
@Log4j
public class JoinController {
	
	@Autowired
	private MemberDao memberDao;
	
	//		/joinus/login.htm ->	/joinus/login.jsp 응답 
	@GetMapping("/login.htm")
	public String login() {	
		return "login.jsp";
	}
	
	
	//		/joinus/join.htm ->	/joinus/join.jsp 응답 
	@GetMapping("/join.htm")
	public String join() {		
		return "join.jsp";
	}
	
	@PostMapping("/join.htm")
	public String join(MemberVO member)throws Exception {	
		this.memberDao.insert(member);
				
		return "redirect:../index.htm"; // 상위로 올라가서 메인페이지로 올라가겠다.
	}
	
} // class
