package org.doit.ik.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.doit.ik.domain.ProductDTO;
import org.doit.ik.domain.ProductImgDTO;
import org.doit.ik.mapper.ProductImgMapper;
import org.doit.ik.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@AllArgsConstructor
@Service
public class ProductServiceImpl  implements ProductService{

	private ProductMapper productMapper;
	private ProductImgMapper productImgMapper;
	
	
	@Override
	public ProductDTO get(Long id) {
		log.info("> productserviceimple.get()...");
		
		try {
			ProductDTO product =  productMapper.view(id);
			List<ProductImgDTO> images = productImgMapper.getImg(id);
			
			Iterator<ProductImgDTO> ir = images.iterator();
			
			while (ir.hasNext()) {
				ProductImgDTO image = (ProductImgDTO)ir.next();
				System.out.println(image.toString());
				//확인
			}//while
			product.setImages(images);
			return product;
			
		} catch (SQLException e) {
			System.out.println("product 출력 실패");
			e.printStackTrace();
		}
		return null;
	}

}
