package org.doit.ik;

import java.io.File;

import org.doit.ik.domain.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("cmr")
public class CmrUploadController {
	
	//contextPath or root
	// (webapp)/cmr/upload
	//ctrl+f11 은 기본 get방식임
	
	@GetMapping(value = "/upload")
	public void upload() {
		log.info("> CmruploadController.upload()...Get");
		//"/WEB-INF/views/"+"cmr/upload/"+".jsp"
	}
	
	
	/*
  	<div><input type="text" name="output" value="hello World!"></div>
  	<div><input type="file" name="attach" ></div>
  	<div><input type="submit" ></div>
  	1.
	@PostMapping(value = "/upload")
	public void upload(
				@RequestParam("output")String output
				,@RequestParam("attach")MultipartFile attach			
				) {
		log.info("> CmruploadController.upload()...Post");
		
		log.info("-".repeat(30));
		log.info("1. output : " +output );
		//c://upload 폴더 생성
		String uploadFolder = "C:\\upload";
		if (!attach.isEmpty()) {
			log.info("-".repeat(30));
			log.info("2. originalFilename " + attach.getOriginalFilename());
			log.info("2. size : " + attach.getSize());
			File saveFile = new File(uploadFolder, attach.getOriginalFilename());
			try {
				attach.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			log.info(" = end = ");
		}//if
	}//upload
	*/
	
	//2. 커맨드 객체를 사용해서 파일 업로드처리
	@PostMapping(value = "/upload")
	public void upload(
		Message message			
				) {
		log.info("> CmruploadController.upload()...Post");
		
		log.info("-".repeat(30));
		log.info("1. output : " +message.getOutput() );
		//c://upload 폴더 생성
		String uploadFolder = "C:\\upload";
		MultipartFile attach = message.getAttach();
		if (!attach.isEmpty()) {
			log.info("-".repeat(30));
			log.info("2. originalFilename " + attach.getOriginalFilename());
			log.info("2. size : " + attach.getSize());
			File saveFile = new File(uploadFolder, attach.getOriginalFilename());
			try {
				attach.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
			log.info(" = end = ");
		}//if
	}//upload

	
}//class
