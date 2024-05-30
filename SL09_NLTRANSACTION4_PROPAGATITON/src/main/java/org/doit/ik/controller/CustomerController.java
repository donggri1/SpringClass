package org.doit.ik.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.doit.ik.service.MemberShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/customer/*")
@Log4j
public class CustomerController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private MemberShipService memberShipService;
	
	// <a class="btn-del button" href="noticeDel.htm?seq=${notice.seq}&filesrc=${ notice.filesrc }">����</a>
	@GetMapping("/noticeDel.htm")
	public String noticeDelete(@RequestParam("seq") String seq
			, @RequestParam("filesrc") String filesrc
			, HttpServletRequest request) throws Exception {
		
		// 1. ÷�������� �ִ� ���������� ��� ÷�����ϵ� ����
		String uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
		File delFile = new File(uploadRealPath, filesrc);
		if(delFile.exists()) {
			delFile.delete();
		}
		
		// 2. �������� �۵� ����
		int rowCount = this.noticeDao.delete(seq);
		if(rowCount == 1) {
			// redirect ���ξ� == response.sendRedirect()
			return "redirect:notice.htm";
		}
		else {
			return "redirect:noticeDetail.htm?seq=" + seq + "&error";
		}
	}
	
	// <a class="btn-edit button" href="noticeEdit.htm?=seq${notice.seq }">����</a>
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq") String seq
	      ,Model model) throws Exception {
	   NoticeVO notice= this.noticeDao.getNotice(seq);
	   model.addAttribute("notice",notice);
	   return "noticeEdit.jsp";
	}
	
	// <input type="text"  name="o_filesrc"  value="${ notice.filesrc }" />
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(NoticeVO notice
			, @RequestParam("o_filesrc") String oFilesrc
			, HttpServletRequest request) throws Exception{
		// 1. ���� ÷�������� �ִ� ��� ���� ÷������ ���õǸ�
		// ���� ÷�������� ���� �ڵ� �߰�
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = null;
		if(!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealPath : " + uploadRealPath);
			
			// ���� ÷�������� �ִٸ� ���� ÷������ ����... �ڵ� �߰�
			File o_file = new File(uploadRealPath, oFilesrc);
			if(o_file.exists()) o_file.delete();
			
			// ���� ÷������ �߰�
			String originalFilename = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			
			File dest = new File(uploadRealPath, filesystemName);
			multipartFile.transferTo(dest); 	// ���� ���� ����
			notice.setFilesrc(filesystemName);
		}
		else {
			notice.setFilesrc(oFilesrc);
		}
		
		// 2.
		int rowCount = this.noticeDao.update(notice);
		if(rowCount == 1) { // �ۼ��� ����
			return "redirect:noticeDetail.htm?seq=" + notice.getSeq(); // ������[�����̷�Ʈ] / ������
		} else { // �ۼ��� ����
			return "redirect:notice.htm";
		}
	}
	
	@GetMapping(value = "/noticeReg.htm")
	public String noticeReg(HttpSession session) throws Exception{
		return "noticeReg.jsp";
	}
	
	
	
	/* [1]
	 @PostMapping(value = "/noticeReg.htm") public String
	 noticeReg(@RequestParam("title") String title , @RequestParam("content")
	 String content) throws Exception{ return "noticeReg.jsp"; }
	 */
	
	// 2. NoticeVO notice Ŀ�ǵ� ��ü( command Object )
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
	      int index = 1;      
	      while( true ) {         
	         File f = new File(uploadRealPath, originalFilename);         
	         if( !f.exists() ) return originalFilename;         
         	// upload ������ originalFilename ������ �����Ѵٴ� �ǹ�         a-2  .txt (4�ڸ�)
	         String fileName = originalFilename.substring(0, originalFilename.length() - 4 );  //   a
	         String ext =  originalFilename.substring(originalFilename.length() - 4 );  // .txt
	         // asdfasf-3.txt
	         originalFilename = fileName+"-"+(index)+ext;

	         index++;
	      } // while 
	}
	
	@PostMapping(value = "/noticeReg.htm") 
	public String noticeReg(NoticeVO notice
			, HttpServletRequest request) throws Exception{
		
		CommonsMultipartFile multipartFile = notice.getFile();
		String uploadRealPath = null;
		if(!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadRealPath : " + uploadRealPath);
			
			String originalFilename = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
			
			File dest = new File(uploadRealPath, filesystemName);
			multipartFile.transferTo(dest); 	// ���� ���� ����
			notice.setFilesrc(filesystemName);
		}
		
		notice.setWriter("youngjin");
		try {
			this.memberShipService.insertAndPointUpOfMember(notice, "youngjin");
			int rowCount = 1;
			return "redirect:notice.htm"; // ������[�����̷�Ʈ] / ������
		} catch (Exception e) {
			return "noticeReg.jsp?error";
		}
		
		
	}
	
	// NoticeController.java => ��Ʈ�ѷ� �޼��� ����
	/* 1.
	@GetMapping(value = "/notice.htm")
	public ModelAndView notices(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView(); 
		
		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");
		
		int page = 1;
		String field = "title";
		String query = "";
		
		if(ppage != null && !ppage.equals("")) page = Integer.parseInt(ppage);
		if(pfield != null && !pfield.equals("")) field = pfield;
		if(pquery != null && !pquery.equals("")) query = pquery;
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		
		mav.addObject("list", list);
		mav.addObject("message", "hello world!");
		mav.setViewName("notice.jsp");
		return mav;
	}
	*/
	// 2.
	@GetMapping(value = "/notice.htm")
	public String notices(HttpServletRequest request, HttpServletResponse response
						, Model model
						, @RequestParam(value = "page", defaultValue = "1") int page
						, @RequestParam(value = "field", defaultValue = "title") String field
						, @RequestParam(value = "query", defaultValue = "") String query) throws Exception {
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		
		model.addAttribute("list", list);
		model.addAttribute("message", "hello world!");

		return "notice.jsp";
	}
	
	// NoticeDetailController.java => ��Ʈ�ѷ� �޼��� ����
	@GetMapping("/noticeDetail.htm")
	public String noticeDetail(@RequestParam("seq") String seq
							, Model model) throws Exception {
		
		NoticeVO notice = this.noticeDao.getNotice(seq);
		
		model.addAttribute("notice", notice);
		return "noticeDetail.jsp";
	}
	
} // class
