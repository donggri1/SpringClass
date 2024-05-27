package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.ProductImgDTO;

public interface ProductImgService  {

	List<ProductImgDTO> getImg(long id);
	
}//clas
