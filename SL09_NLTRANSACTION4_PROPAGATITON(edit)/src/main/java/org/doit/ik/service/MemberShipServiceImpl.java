package org.doit.ik.service;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberShipServiceImpl implements MemberShipService{

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	@Transactional
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws Exception {
		noticeDao.insert(notice);
 		// title UK
 		notice.setTitle(notice.getTitle()+"-2");
 		noticeDao.insert(notice);
	}

}//class
