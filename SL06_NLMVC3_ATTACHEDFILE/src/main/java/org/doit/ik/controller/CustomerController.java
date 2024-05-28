package org.doit.ik.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.WindowConstants;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import freemarker.core.ReturnInstruction.Return;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/customer/*")
@Log4j
public class CustomerController {
	
	@Autowired
	private NoticeDao noticeDao ;

	//<a class="btn-del button" href="noticeDel.htm?seq=${ notice.seq }">삭제</a>
	@GetMapping(value = "/noticeDel.htm")
	public String noticeDel(@RequestParam("seq")String seq) throws Exception {
		int rowCount = this.noticeDao.delete(seq);
		
		if (rowCount==1) {
			return "redirect:notice.htm?rowCount=" +rowCount;
		}else {
			return "redirect:noticeDetail.htm?seq="+seq+"&error";
		}
		
		
		
	};
	
	//<button class="btn-save button" type="submit">수정</button>
	@PostMapping(value = "/noticeEdit.htm")
	public String noticeEdit(NoticeVO notice) throws Exception{	
		int rowCount = this.noticeDao.update(notice);
		if (rowCount==1) {//글수정성공
			return "redirect:noticeDetail.htm?seq="+notice.getSeq(); // 스프링 [리다이렉트] / 포워딩
		}else {//글수정 실패
			return "redirect:notice.htm";
			//return "noticeEdit.htm?seq="+notice.getSeq();
		}
	}	//noticeReg
	
	
	
	//<a class="btn-edit button" href="noticeEdit.htm?seq=${ notice.seq }">수정</a>
	@GetMapping(value = "/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq")String seq
							,Model model
							) throws Exception {
		NoticeVO notice= this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		
		return "noticeEdit.jsp";
	}
	
	
	//저장버튼을 누를때
	// NoticeVO notice == 커맨드객체로 입력받기(command object) 
	@PostMapping(value = "/noticeReg.htm")
	public String noticeReg(NoticeVO notice,HttpServletRequest request) throws Exception{	
		CommonsMultipartFile multipartFile = notice.getMultipartFile();
		String uploadRealPath = null;
		uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		System.out.println("> uploadRealPath : "+uploadRealPath);
		if (!multipartFile.isEmpty()) {
			
		}//if
		
		int rowCount = this.noticeDao.insert(notice);
		if (rowCount==1) {//글쓰기성공
			return "redirect:notice.htm"; // 스프링 [리다이렉트] / 포워딩
		}else {//글쓰기 실패
			return "noticeReg.jsp?error";
		}
	}	//noticeReg
	
	/*
	 * //저장버튼을 누를때 
	 * @PostMapping(value = "/noticeReg.htm") public String
	 * noticeReg(@RequestParam("title")String title ,@RequestParam("content")String
	 * content) throws Exception{ return "noticeReg.jsp"; }
	 */
	//글쓰기
	@GetMapping(value = "/noticeReg.htm")
	public String noticeReg(HttpSession session) throws Exception{
		
		
		return "noticeReg.jsp";
	}
	
	//NoticeController.java => 컨트롤러 메서드로 구현
	@GetMapping(value = "/notice.htm")
	public String notices(
			Model model
			,@RequestParam(value = "page", defaultValue = "1")int page
			,@RequestParam(value = "field", defaultValue = "title")String field
			,@RequestParam(value = "query", defaultValue = "")String query
			) throws Exception {


		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);	
		model.addAttribute("list", list);
		model.addAttribute("message", "hello World");
		return "notice.jsp";
	}//notice
	/*1번
	@GetMapping(value = "/notice.htm")
	public ModelAndView notices(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		// ModelAndView 타입  p284 참조
		// 1) Model
		// 2) View
		ModelAndView mav = new ModelAndView();
		
		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");
		
		int page = 1;
		String field = "title";
		String query = "";
		 
		if( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage);
		if( pfield != null && !pfield.equals("") ) field = pfield;
		if( pquery != null && !pquery.equals("") ) query = pquery;
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		
		mav.addObject("list", list);
		mav.addObject("message", "hello world!");
		mav.setViewName("notice.jsp");
		return mav;
	}//notice
	*/
	
	//NoticeDetailController => 컨트롤러 메서드 구현
	@GetMapping(value = "/noticeDetail.htm")
	public String noticeDetail(
			@RequestParam("seq")String seq
			,Model model
			) throws Exception {
		 
		NoticeVO notice = this.noticeDao.getNotice(seq);
		
		model.addAttribute("notice", notice);
		// mav.setViewName("noticeDetail.jsp");
		return "noticeDetail.jsp";
	}
	
	/*
	@GetMapping(value = "/noticeDetail.htm")
	public ModelAndView noticeDetail(
			HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		 
		String viewName = "noticeDetail.jsp";
		ModelAndView mav = new ModelAndView(viewName);
		
		String seq = request.getParameter("seq");		
		 
		NoticeVO notice = this.noticeDao.getNotice(seq);
		
		mav.addObject("notice", notice);
		// mav.setViewName("noticeDetail.jsp");
		return mav;
	}
	*/
 
}//class
