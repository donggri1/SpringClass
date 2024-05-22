package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;

public interface BoardMapper {
	
	List<BoardVO> getList();
	
	void insert(BoardVO boardVO); // ���� ����
	void insertSelectKey(BoardVO boardVO); // ���� ���� + PK(�� ��ȣ)
	
	BoardVO read(long bno);
	
	int update(BoardVO boardVO);
	int delete(long bno);
	
}
