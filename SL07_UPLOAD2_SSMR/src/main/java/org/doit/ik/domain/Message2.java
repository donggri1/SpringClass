package org.doit.ik.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Message2 { 
	
	private String output;
	private List<MultipartFile> attachList;

	
	
}//class

