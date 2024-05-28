package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 요청URL: /customer/notice.htm?page=2&field=title&query=홍길동
//공지사항 목록가져오는 컨트롤러
public class NoticeController implements Controller{

	private NoticeDao noticeDao;
	
	public NoticeController() {}
	//생성자 DI (생성자를입력받아 생성자 생성)
	public NoticeController(NoticeDao noticeDao){
		this.noticeDao=noticeDao;
	}	
	// /customer/notice.htm?page=2&field=title&query=홍길동
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ModelAndView 타입 p.284 참조
		// 1) Model
		// 2) View
		ModelAndView mav = new ModelAndView();
		
		String ppage = request.getParameter("page");
		String pfield= request.getParameter("field");
		String pquery = request.getParameter("query");
		
		int page = 1;
		String field = "title";
		String query = "";
		
		if(ppage !=null && !ppage.equals("")) page = Integer.parseInt(ppage);
		if(pfield !=null && !ppage.equals("")) field = pfield;
		if(pquery !=null && !ppage.equals("")) query = pquery;
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		
		mav.addObject("list", list);
		mav.addObject("message", "hello world!");
		mav.setViewName("notice.jsp");
		return mav;
	}
		
}//class












