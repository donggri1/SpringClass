package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;


public interface MemberDao {
	MemberVO getMember(String id) throws ClassNotFoundException, SQLException;
	
	// 회원가입 메서드
	int insert(MemberVO member) throws ClassNotFoundException, SQLException;
		
}
