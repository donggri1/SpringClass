package org.doit.ik;

import java.util.List;

import org.doit.ik.domain.EmpDTO;
import org.doit.ik.domain.TicketDTO;
import org.doit.ik.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("scott/*")
public class RESTController {
	
	@Autowired
	private EmpMapper empMapper;
	
	@GetMapping("/getText")
	public String getText() {
		log.info("> MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return "하이";
	}

	@GetMapping(value = "/getText2", produces = "text/plan; charset=UTF-8")
	public String getText2() {
		log.info("> MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		return "하이";
	}
	@GetMapping(value = "/employees")
	public List<EmpDTO> selectALL() {
		return this.empMapper.selectAll();
	}
	@GetMapping(value = "/employees2",produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE
	})
	public List<EmpDTO> selectALL2() {
		return this.empMapper.selectAll();
	}
	
	@GetMapping(value = "/employees3",produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE
			, MediaType.APPLICATION_XML_VALUE
	})
	public List<EmpDTO> selectALL3() {
		return this.empMapper.selectAll();
	}

	@GetMapping(value = "/employee/{empno}"
			,produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE
			, MediaType.APPLICATION_XML_VALUE
	})
	public EmpDTO selectByEmpno(@PathVariable("empno")int empno) {
		return this.empMapper.selectByEmpno(empno);
	}
	
	@PostMapping(value = "/ticket", produces = 
					{ MediaType.APPLICATION_JSON_UTF8_VALUE })
	   public TicketDTO convert(@RequestBody TicketDTO ticket) {      
	      ticket.setOwner( ticket.getOwner() + "님" );
	      log.info(">> "+ ticket);
	      return ticket;
	   }
	@PostMapping(value = "/idCheck",produces 
			= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public int idCheck(String empno) {
		return this.empMapper.idCheck(empno);
	}
	
}///
