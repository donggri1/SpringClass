package org.doit.ik;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;

 
@Controller
public class ScottController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScottController.class);
	
	//@Autowired
	@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;
	
	@Autowired
	private EmpMapper empMapper;

	//@RequestMapping(value = "/scott/dept", method = RequestMethod.GET)
	@GetMapping("/scott/dept")
	public String dept(Locale locale, Model model) {
		logger.info("> ScottController.dept()called..");
		
		
		ArrayList<DeptDTO> list =  this.deptMapper.selectDept();
		model.addAttribute("list",list);
		
		return "/scott/dept"; //WEB-INF/views/time.jsp
	}
	
	//@PostMapping(value = "/scott/emp") 한개일경우 생략가능
	//?deptno=10&deptno=20&deptno=30 
	//?deptno=10&deptno=20&deptno=30 int [] 
	//?deptno=10&deptno=20&deptno=30 ArrayList<Integer> 자동
	@PostMapping("/scott/emp")
	public void emp(@RequestParam(value = "deptno")int [] deptnos
			,Model model) {		
		logger.info("> ScottController.emp()called..");
	
		List<EmpDTO> list = this.empMapper.selectEmpArray(deptnos);
		model.addAttribute("list",list);
		
//		return "/scott/dept/"; 필요없음그냥 보이드처리
	}
	
	
	
	
}//class












