package org.doit.ik.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptEmpSalgradeDTO {
	
	private int deptno;
	private String dname;
	private String loc;
	
	private List<EmpDTO> empList;
	
	


}//class
