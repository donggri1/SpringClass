package org.doit.ik;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Setter;


@Controller
public class ScottController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScottController.class);
	
	//@Autowired
	@Setter(onMethod=@__({@Autowired}))
	private DeptMapper deptMapper;

	//@RequestMapping(value = "/scott/dept", method = RequestMethod.GET)
	@GetMapping("/scott/dept")
	public String home(Locale locale, Model model) {
		logger.info("> ScottController.dept()called..");
		
		
		ArrayList<DeptDTO> list =  this.deptMapper.selectDept();
		model.addAttribute("list",list);
		
		return "/scott/dept"; //WEB-INF/views/time.jsp
	}
	
}
