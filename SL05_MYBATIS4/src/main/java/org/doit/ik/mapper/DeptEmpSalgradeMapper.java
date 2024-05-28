package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.DeptEmpSalgradeDTO;
import org.doit.ik.domain.EmpDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface DeptEmpSalgradeMapper {
	
	// 모든 부서정보 select
	List<DeptEmpSalgradeDTO> getDept();
	// 해당 부서의 사원정보 select
	List<EmpDTO> getEmpOfDept(int deptno);
}//매퍼
