package org.doit.ik.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 요청URL: /customer/notice.htm?seq=2
//공지사항 보기 컨트롤러
public class NoticeDetailController implements Controller{

	private NoticeDao noticeDao;	
	public NoticeDetailController() {}
	
	//setter DI
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
			
	// /customer/notice.htm?page=2&field=title&query=홍길동
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// ModelAndView 타입 p.284 참조
		// 1) Model
		// 2) View
		
		String viewName = "noticeDetail.jsp";
		ModelAndView mav = new ModelAndView(viewName);
		
		String pseq= request.getParameter("seq");
		
		
		
		NoticeVO notice = this.noticeDao.getNotice(pseq);
		
		mav.addObject("notice", notice);
		//mav.setViewName("noticeDetail.jsp");
		return mav;
	}

}//class












