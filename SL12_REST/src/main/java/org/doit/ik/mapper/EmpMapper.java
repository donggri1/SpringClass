package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.EmpDTO;

public interface EmpMapper {

		List<EmpDTO> selectAll();
		EmpDTO selectByEmpno(int empno);
		int idCheck(String empno);
}//
