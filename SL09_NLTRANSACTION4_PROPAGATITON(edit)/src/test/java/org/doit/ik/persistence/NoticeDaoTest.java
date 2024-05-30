package org.doit.ik.persistence;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.junit.jupiter.api.Test;

class NoticeDaoTest {

	@Test
	void testNoticeInsert() {
		NoticeDao noticeDao = new NoticeDaoImpl();
		
		NoticeVO notice = new NoticeVO();
		notice.setTitle("ù ��° �Խñ�");
		notice.setContent("ù ��° �Խñ� ����");
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
