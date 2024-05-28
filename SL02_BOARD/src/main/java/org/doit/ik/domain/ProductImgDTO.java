package org.doit.ik.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgDTO {
 
	private int id ;
	private long productId;  
	private String imgUrl; 
	private String imgContent;

}//class
