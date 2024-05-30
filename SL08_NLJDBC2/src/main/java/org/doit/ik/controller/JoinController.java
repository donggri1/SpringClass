package org.doit.ik.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.MemberDao;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
