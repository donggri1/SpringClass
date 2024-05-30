package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.stereotype.Repository;

@Repository
// @Transactional => ���� �ִ°� ��� transaction ó���ϰڴ�
public interface NoticeDao {
	
	// ���������� ���� ��ȯ�ϴ� �޼���
	int getCount(String field, String query) throws ClassNotFoundException, SQLException;

	// ���������� ����� List �÷������� ��ȯ
	List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	int delete(String seq) throws ClassNotFoundException, SQLException;
	
	int update(NoticeVO notice) throws ClassNotFoundException, SQLException;

	NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;

	int insert(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
	// Ʈ����� ó���� ���� �޼��� �߰�
	// public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException; 
	
}
