package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


public interface NoticeDao {
   

   
   // 공지사항의 갯수 반환하는 메서드
    int getCount(String field, String query) throws ClassNotFoundException, SQLException;
  
   
   // 공지사항의 목록을 List컬렉션으로 반환하는 메서드
    List<NoticeVO> getNotices( int page //현재 페이지 번호
	         , String field    // 검색조건
	         , String query    // 검색어
	         ) throws ClassNotFoundException, SQLException;
 
   
   // 공지사항 삭제하는 메서드
    int delete(String seq) throws ClassNotFoundException, SQLException;
   

   
   // 공지사항 수정하는 메서드
    int update(NoticeVO notice) throws ClassNotFoundException, SQLException;
 
   
   // 공지사항 보기
    NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;
 

   // 공지사항 추가하는 메서드
    int insert(NoticeVO notice) throws ClassNotFoundException, SQLException ;
  
}