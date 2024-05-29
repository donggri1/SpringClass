package org.doit.ik;

import java.io.File;
import java.util.List;

import org.doit.ik.domain.Message;
import org.doit.ik.domain.Message2;
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
public class CmrUploadController2 {

	//contextPath or root
	// (webapp)/cmr/upload
	//ctrl+f11 은 기본 get방식임

	@GetMapping(value = "/upload2")
	public void upload() {
		log.info("> CmruploadController.upload2()...Get");
		//"/WEB-INF/views/"+"cmr/upload/"+".jsp"
	}


	/*
	 * //<div><input type="text" name="output" value="hello World!"></div>
	 * //<div><input type="file" name="attach" multiple="multiple"></div>
	 * //<div><input type="submit" ></div> // 1.
	 * 
	 * @PostMapping(value = "/upload2") public void upload(
	 * 
	 * @RequestParam("output")String output
	 * ,@RequestParam("attach")List<MultipartFile> attachList ) {
	 * log.info("> CmruploadController.upload2()...Post");
	 * 
	 * log.info("-".repeat(30)); log.info("1. output : " +output ); //c://upload 폴더
	 * 생성 String uploadFolder = "C:\\upload";
	 * 
	 * for (MultipartFile attach : attachList) {
	 * 
	 * if (!attach.isEmpty()) { log.info("-".repeat(30));
	 * log.info("2. originalFilename " + attach.getOriginalFilename());
	 * log.info("2. size : " + attach.getSize()); File saveFile = new
	 * File(uploadFolder, attach.getOriginalFilename()); try {
	 * attach.transferTo(saveFile); } catch (Exception e) {
	 * log.error(e.getMessage()); }
	 * 
	 * log.info(" = end = "); }//if }//for
	 * 
	 * }//upload
	 */


	//2. 커맨드 객체를 사용해서 파일 업로드처리 여러개의 파일을 업로드	  
	@PostMapping(value = "/upload2")
	public void upload(Message2 message2 ) {
		log.info("> CmruploadController.upload2()...Post");

		log.info("-".repeat(30)); log.info("1. output : " +message2.getOutput() );
		//c://upload 폴더 생성 String uploadFolder = "C:\\upload"; MultipartFile attach =
		List<CommonsMultipartFile> attachList = message2.getAttachList();
		String uploadFolder = "C:\\upload";
		
		for (CommonsMultipartFile attach : attachList) {

			if (!attach.isEmpty()) { log.info("-".repeat(30));
			log.info("2. originalFilename " + attach.getOriginalFilename());
			log.info("2. size : " + attach.getSize()); 
			File saveFile = new
					File(uploadFolder, attach.getOriginalFilename());
			try {
				attach.transferTo(saveFile); } 
			catch (Exception e) {
				log.error(e.getMessage()); }

			log.info(" = end = "); }//if }//upload
		}//for
		
	}//upload




}//class
