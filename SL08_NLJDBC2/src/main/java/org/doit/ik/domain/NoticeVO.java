package org.doit.ik.domain;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO {
	
	private String seq;
	private String title;
	private String writer;
	private Date regdate;
	private String filesrc;
	private int hit;
	private String content;
	
	private CommonsMultipartFile file;
}
