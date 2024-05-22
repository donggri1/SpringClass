package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.mapper.BoardMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor // ������ 4.3���� ������ DI�� ���ؼ� �ڵ����� ��ü�� ������
@Service
public class BoardServiceImpl implements BoardService {

	//@Autowired
	//@Setter
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("> BoardServiceImpl.getList()...");
		return this.boardMapper.getList();
	}

	@Override
	public void register(BoardVO boardVO) {
		log.info("> BoardServiceImpl.register()...");
		//this.boardMapper.insert(boardVO);
		this.boardMapper.insertSelectKey(boardVO);
		
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("> BoardServiceImpl.get()...");
		return this.boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO boardVO) {
		log.info("> BoardServiceImpl.modify()...");
		
		return this.boardMapper.update(boardVO)==1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("> BoardServiceImpl.remove()...");
		
		return this.boardMapper.delete(bno)==1;
	}

}
