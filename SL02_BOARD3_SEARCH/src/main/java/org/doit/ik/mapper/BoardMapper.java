package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardMapper {
	
	List<BoardVO> getList();
	List<BoardVO> getListWithPaging(Criteria criteria);
	int getTotalCount ( Criteria criteria);
	
	void insert(BoardVO boardVO); // 새글 쓰기
	void insertSelectKey(BoardVO boardVO); // 새글 쓰기 + PK(글 번호)
	
	BoardVO read(long bno);
	
	int update(BoardVO boardVO);
	int delete(long bno);
	
}
