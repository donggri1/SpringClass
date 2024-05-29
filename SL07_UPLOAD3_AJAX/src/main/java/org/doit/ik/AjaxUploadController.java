package org.doit.ik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("ajax")
public class AjaxUploadController {
	
	//contextPath or root
	// (webapp)/ajax/upload
	//ctrl+f11 은 기본 get방식임
	
	@GetMapping(value = "/upload")
	public void upload() {
		log.info("> AjaxUploadController.upload()...Get");
		//"/WEB-INF/views/"+"ajax/upload/"+".jsp"
	}// getUp
	
	

	
}//class
