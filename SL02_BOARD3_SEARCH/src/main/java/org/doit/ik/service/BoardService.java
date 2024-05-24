package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;

public interface BoardService {

		List<BoardVO> getList();
		void register(BoardVO boardVO);
		BoardVO get(Long bno);
		boolean modify(BoardVO boardVO);
		boolean remove(Long bno);
		
		List<BoardVO> getListWithPaging(Criteria criteria);
		int getTotal(Criteria criteria);
}//class
