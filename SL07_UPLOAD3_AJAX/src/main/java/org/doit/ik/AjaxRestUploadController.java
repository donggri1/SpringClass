package org.doit.ik;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("ajax")
public class AjaxRestUploadController {
	

	@PostMapping(value = "uploadAjax")
	public void uploadAjax(
			@RequestParam("attachList") List<MultipartFile> attachList
			) {
		
		log.info("> AjaxuploadController.uploadAjax()...Post");
		  

		  String uploadFolder = "C:\\upload";
		  
		  for (MultipartFile attach : attachList) {
		  
		  if (!attach.isEmpty()) { log.info("-".repeat(30));
		  log.info("2. originalFilename " + attach.getOriginalFilename());
		 log.info("2. size : " + attach.getSize());
		 File saveFile = new File(uploadFolder, attach.getOriginalFilename()); try {
		 attach.transferTo(saveFile); } catch (Exception e) {
		  log.error(e.getMessage()); }
		  
		  log.info(" = end = "); }//if }//for
		
		  }//for
	}//post up

	
}//class
