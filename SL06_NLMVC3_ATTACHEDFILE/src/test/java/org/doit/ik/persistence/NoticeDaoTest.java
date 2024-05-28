package org.doit.ik.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.junit.jupiter.api.Test;

class NoticeDaoTest {

	@Test
	void testNoticeInsert() {
		NoticeDao noticeDao = new NoticeDao();
		
		NoticeVO notice = new NoticeVO();
		notice.setTitle("첫 번째 게시글");
		notice.setContent("첫 번째 게시글 내용");		
		int rowCount = 0;
		try {
			rowCount = noticeDao.insert(notice);
		    System.out.println(rowCount);
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}

}
