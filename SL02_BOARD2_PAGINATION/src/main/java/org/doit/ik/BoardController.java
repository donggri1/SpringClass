package org.doit.ik;

import org.apache.tiles.request.Request;
import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor
public class BoardController {

	// @Autowired
	private BoardService boardService;

	// 페이징 처리가안된 컨트롤러 메서드
	// http://localhost/board/list 게시글 목록 요청 URL
	/*
	 * @GetMapping("/list") public void list(Model model) {
	 * log.info("> BoardController.list()..."); model.addAttribute("list",
	 * this.boardService.getList());
	 * 
	 * }// list
	 */		

	//페이징 처리가된 컨트롤러 메서드
	// http://localhost/board/list			1		5
	// http://localhost/board/list?pageNum=3&amount=10
	@GetMapping("/list")
	public void list(Model model, Criteria criteria) {
		log.info("> BoardController.list()...");		
		model.addAttribute("list", this.boardService.getListWithPaging(criteria));
		// 1 2 [3] 4 5 6 7
		int total = this.boardService.getTotal(criteria);
		new PageDTO(criteria, total);
		model.addAttribute("pageMaker",new PageDTO(criteria,total));
	}// list
	
	
	// <a href= '/board/register'>
	@GetMapping("/register")
	public void register(Model model) {
		log.info("> BoardController.register()GET...");

	}// list

	// <form action= '/board/register' method='post'>
	@PostMapping("/register")
	public String register(BoardVO boardVO, RedirectAttributes rttr) {
		log.info("> BoardController.register()POST...");
		this.boardService.register(boardVO);

		rttr.addFlashAttribute("result", boardVO.getBno());
		return "redirect:/board/list";

	}// register

	// <a href="/board/get?bno= 2">
	// <a href="/board/get/2">
	/*
	 * @GetMapping(value = {"/get/{bno}", "/modify/{bno}"} ) public void get(Model
	 * model,@RequestParam("bno") Long bno) { public String get(Model
	 * model,@PathVariable("bno") Long bno) {
	 * log.info("> BoardController.get()..."); model.addAttribute("boardVO",
	 * this.boardService.get(bno)); return "/board/get";
	 * //WEB-INF/views/board/get+.jsp }//list
	 * 
	 */
	/*
	@GetMapping(value = {"/get", "/modify"} )
	public void get(Model model,@RequestParam("bno") Long bno,Criteria criteria) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO", this.boardService.get(bno));
		model.addAttribute("criteria",criteria);
	}//list
	*/
	
	@GetMapping(value = {"/get", "/modify"} )
	public void get(Model model,@RequestParam("bno") Long bno
			,@ModelAttribute("criteria")Criteria criteria) {
		log.info("> BoardController.get()...");
		model.addAttribute("boardVO", this.boardService.get(bno));
		
	}//list	
	
	
	@PostMapping(value = {"/modify"})
	public String modify(BoardVO boardVO,RedirectAttributes rttr
			,@ModelAttribute("criteria")Criteria criteria) {
		log.info("> BoardController.modify()...");
		if (this.boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result", "success");
		}//if
		
		//일회성 ? 체크
		rttr.addFlashAttribute("pageNum",criteria.getPageNum());
		rttr.addFlashAttribute("amount", criteria.getAmount());
		
		return "redirect:/board/list?pageNum="+criteria.getPageNum();
		//return String.format("redirect:/board/get?bno=%d",boardVO.getBno());
	}//list
	
	@GetMapping("/remove")
	public String remove(Model model,@RequestParam("bno") Long bno
			,RedirectAttributes rttr) {
		log.info("> BoardController.remove()...");
		if (boardService.remove(bno) ) {
			rttr.addFlashAttribute("result","success");
		}//if
		return "redirect:/board/list"; 
	}//list
	
	
	
}// class
