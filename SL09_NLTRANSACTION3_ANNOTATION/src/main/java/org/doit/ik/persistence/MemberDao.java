package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;


public interface MemberDao {
	//public은 없어도됨 생략임
	MemberVO getMember(String id) throws ClassNotFoundException, SQLException;
	

	int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	
}