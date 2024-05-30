package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.stereotype.Repository;

@Repository
// @Transactional => 여기 있는거 모두 transaction 처리하겠다
public interface NoticeDao {
	
	// 공지사항의 개수 반환하는 메서드
	int getCount(String field, String query) throws ClassNotFoundException, SQLException;

	// 공지사항의 목록을 List 컬렉션으로 반환
	List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	int delete(String seq) throws ClassNotFoundException, SQLException;
	
	int update(NoticeVO notice) throws ClassNotFoundException, SQLException;

	NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;

	int insert(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
	// 트랜잭션 처리를 위한 메서드 추가
	// public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException; 
	
}
