package org.doit.ik.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.doit.ik.service.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/customer/*")
@Log4j
@Service
public class CustomerController {

	@Autowired
	private NoticeMapper noticeDao;

	@Autowired
	private MemberShipService memberShipService;
	//<a class="btn-del button" href="noticeDel.htm?seq=${notice.seq }">삭제</a>
	/*
	 * <a class="btn-del button"
	 * href="noticeDel.htm?seq=${notice.seq}&filesrc=${ notice.filesrc }">삭제</a>
	 */


	@GetMapping("/noticeDel.htm")
	public String noticeDel(@RequestParam("seq") String seq
			,@RequestParam("filesrc") String filesrc
			, HttpServletRequest request
			) throws Exception{

		//1. 첨부파일이 있는 공지사항일 경우 첨부파일도 삭제
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");

		File delFile = new File(uploadRealPath, filesrc);
		if (delFile.exists()) {
			delFile.delete();
		}
		//2. 공지사항 글도 삭제
		int rowCount = this.noticeDao.delete(seq);
		if (rowCount==1) {	// 글삭제 성공
			//redirect 접두어 == response.sendRedirect()
			return "redirect:notice.htm";
		} else {
			return "redirect:noticeDetail.htm?seq="+seq +"&error";
		}


	}


	// <button class="btn-save button" type="submit" >수정</button>
	// <input type="text" name="o_filesrc" value="${ notice.filesrc}" />
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(NoticeVO notice
			, @RequestParam("o_filesrc") String oFilesrc
			, HttpServletRequest request
			) throws Exception{
		// 1. 원래 첨부파일이 있는 경우 새로 첨부파일 선택되면
		//    이전 첨부파일은 삭제하는 작업 추가
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = null;
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealPath : " + uploadRealPath);

			// 1-1.이전 첨부파일이 있다면 이전 첨부파일 삭제하는 작업하기
			
			File o_file = new File(uploadRealPath,oFilesrc);
			if (o_file.exists()) o_file.delete();
			
			// 1-2. 새로 첨부된 파일 저장
			String originalFilename = multipartFile.getOriginalFilename();

			String fileSystemName = getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath,fileSystemName);
			multipartFile.transferTo(dest); //실제 파일 저장

			notice.setFilesrc(fileSystemName);
			
			
		} else {
			notice.setFilesrc(oFilesrc);
		} // if
		int rowCount = this.noticeDao.update(notice);
		if (rowCount==1) {//수정 성공
			return "redirect:noticeDetail.htm?seq=" + notice.getSeq(); 

			// 스프링 [리다이렉트]/ 포워딩 :redirect 붙이면 리다이렉트기능
		} else {//수정 실패
			return "redirect:notice.htm";
		}


	}


	//	<a class="btn-edit button" href="noticeEdit.htm?seq=${notice.seq }">수정</a>
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq") String seq, Model model)  throws Exception{
		NoticeVO notice =  this.noticeDao.getNotice(seq);

		model.addAttribute("notice",notice);
		return "customer.noticeEdit";
	}



	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;      
		while( true ) {         
			File f = new File(uploadRealPath, originalFilename);         
			if( !f.exists() ) return originalFilename;         
			// upload 폴더에 originalFilename 파일이 존재한다는 의미         a-2  .txt (4자리)
			String fileName = originalFilename.substring(0, originalFilename.length() - 4 );  //   a
			String ext =  originalFilename.substring(originalFilename.length() - 4 );  // .txt
			// asdfasf-3.txt
			originalFilename = fileName+"-"+(index)+ext;

			index++;
		} // while 
	}



	// <a class="btn-write button" href="noticeReg.htm">글쓰기</a>
	// 2. NoticeVO notice 커맨드 객체( command object )
	@PostMapping( value = "/noticeReg.htm")
	public String noticeReg(NoticeVO notice
			, HttpServletRequest request
			) throws Exception{
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = null;
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealPath : " + uploadRealPath);

			String originalFilename = multipartFile.getOriginalFilename();


			String fileSystemName = getFileNameCheck(uploadRealPath, originalFilename);
			File dest = new File(uploadRealPath,fileSystemName);

			multipartFile.transferTo(dest); //실제 파일 저장

			notice.setFilesrc(fileSystemName);
		} // if


		notice.setWriter("KIM");
		int rowCount = this.noticeDao.insert(notice);
//		try {
//			this.memberShipService.insertAndPointUpOfMember(notice, "kkgeon");
//			int rowCount = 1;
		if (rowCount==1) {
			
			return "redirect:notice.htm"; 
		} else  {
			return "noticeReg.jsp?error";
		}
	}
	/*
	@PostMapping("/noticeReg.htm")
	public String noticeReg(@RequestParam("title") String title
			,@RequestParam("content") String content
			) throws Exception{

		return "noticeReg.jsp";
	}
	 */	
	@GetMapping("/noticeReg.htm")
	public String noticeReg(HttpSession session) throws Exception{



		return "customer.noticeReg";
	}


	/*	//1
	 * @GetMapping(value = "/notice.htm") public ModelAndView
	 * notices(HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { // ModelAndView 타입 p284 참조 // 1) Model - for 저장 // 2) View
	 * ModelAndView mav = new ModelAndView();
	 * 
	 * String ppage = request.getParameter("page"); String pfield =
	 * request.getParameter("field"); String pquery = request.getParameter("query");
	 * 
	 * int page = 1; String field = "title"; String query ="";
	 * 
	 * if ( ppage != null && !ppage.equals("") ) page = Integer.parseInt(ppage); if
	 * ( pfield != null && !ppage.equals("") ) field = pfield; if ( pquery != null
	 * && !ppage.equals("") ) query = pquery;
	 * 
	 * List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
	 * 
	 * 
	 * mav.addObject("list", list); mav.addObject("message", "hello world!");
	 * mav.setViewName("notice.jsp"); return mav; 
	 * }
	 */


	//2
	@GetMapping(value = "/notice.htm") 
	public String notices(HttpServletRequest request
			,HttpServletResponse response
			,Model model
			,@RequestParam(value = "page",defaultValue = "1") int page
			,@RequestParam(value = "field",defaultValue = "title") String field
			,@RequestParam(value = "query",defaultValue = "") String query

			) throws Exception { // ModelAndView 타입 p284 참조 // 1) Model - for 저장 // 2) View


		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		model.addAttribute("list", list); 
		model.addAttribute("message", "hello world!");
		return "customer.notice"; 
	}

	//NoticeController.java => 컨트롤러 메서드 구현


	@GetMapping(value = "/noticeDetail.htm")
	public String noticeDetail(
			@RequestParam("seq") String seq
			,Model model
			) throws Exception {
		this.noticeDao.hitUp(seq); //조회수 증가
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);
		return "customer.noticeDetail";
	}
}//class
