package org.doit.ik;

import org.doit.ik.mapper.DeptEmpSalgradeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
@AllArgsConstructor // 스프링 4부터는 autowired가 여기에 포함돼있음
public class DeptEmpMybatisController {

	//@autowired
	private DeptEmpSalgradeMapper deptEmpSalgradeMapper;
	
	@GetMapping("/dept/emp")
	public void getDeptEmpSalgrade(Model model) {
		log.info("> DeptEmpSalgradeMapper.getDeptEmpSalgrade() ... ");
		model.addAttribute("list",this.deptEmpSalgradeMapper.getDeptEmpInfo()) ;
		
	}//get
	
	
}//class


