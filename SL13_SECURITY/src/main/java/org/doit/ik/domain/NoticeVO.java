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
	private String filesrc; //수정
	

	private int hit; //조회수
	private String content;
	
	// p445 참고
	// 스프링에서 지우너하는 파일 업로드 기능을 (4) 방법인
	//커맨드 객체를 이용하는 방법
	private CommonsMultipartFile file; 

}
