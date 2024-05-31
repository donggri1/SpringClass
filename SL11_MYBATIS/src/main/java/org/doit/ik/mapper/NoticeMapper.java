package org.doit.ik.mapper;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.stereotype.Repository;

@Repository
// @Transactional => ���� �ִ°� ��� transaction ó���ϰڴ�
public interface NoticeMapper {
	
	// 공지사항 갯수가져오는함스
	int getCount(String field, String query) throws ClassNotFoundException, SQLException;

	// 
	List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	int delete(String seq) throws ClassNotFoundException, SQLException;
	
	int update(NoticeVO notice) throws ClassNotFoundException, SQLException;

	NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;

	int insert(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
	// Ʈ����� ó���� ���� �޼��� �߰�
	// public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException; 
	
	public void hitUp(String seq) throws ClassNotFoundException, SQLException;
	
	public int getHit(String seq) throws ClassNotFoundException, SQLException;
	
}