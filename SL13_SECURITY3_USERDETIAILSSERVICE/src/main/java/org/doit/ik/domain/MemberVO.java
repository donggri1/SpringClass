package org.doit.ik.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	
	//member 테이블의 컬러명과 필드명 동일...
	private String id;	//수정 uid-id
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar; //수정
	private String cphone; //수정
	private String email;
	private String habit;
	private Date   regdate; //수정
	
	private int    point;
	
	// 권한 정보
	private boolean enabled;
	private List<AuthVO> authList; // 권한 리스트 
	
	
	
}//class





















