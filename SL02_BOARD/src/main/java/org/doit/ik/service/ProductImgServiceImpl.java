package org.doit.ik.service;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.ProductImgDTO;
import org.doit.ik.mapper.ProductImgMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@Log4j
@AllArgsConstructor
@Service
public class ProductImgServiceImpl implements ProductImgService {

	private ProductImgMapper productImgMapper;
	

	@Override
	public List<ProductImgDTO> getImg(long id) {
		log.info("> ProductImgserviceimpl..getList");
		try {
			return this.productImgMapper.getImg(id);
		} catch (SQLException e) {
			System.out.println("이미지 불러오기실패");
			e.printStackTrace();
		}
		return null;
	}

}
