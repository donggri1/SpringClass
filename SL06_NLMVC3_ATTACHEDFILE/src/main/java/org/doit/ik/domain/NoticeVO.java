package org.doit.ik.domain;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
	
	private String seq;
	private String title;
	private String writer;
	private Date regdate;
	private String filesrc; // 수정
	private int hit;
	private String content;
	//commons 를 준이유는 명시돼있어서 그냥 멀티파트 써도됨
	// 커맨드 객체 이용방법
	// <input type="file" id="txtFile" name="file"/>
	private CommonsMultipartFile multipartFile;
	
}






































