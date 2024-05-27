package org.doit.ik.mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.doit.ik.domain.ProductDTO;

public interface ProductMapper {

	ProductDTO view(long  id) throws SQLException;
	
	
	
	
}//class
