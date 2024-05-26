package org.doit.ik.mapper;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.ProductImgDTO;

public interface ProductImgMapper {
	List<ProductImgDTO> getImg(long productId) throws SQLException;
	
}//class
