package org.doit.ik;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

// Ajax ó���ϴ� ��Ʈ�ѷ�
@RestController
@Log4j //== private static final Logger logger = LoggerFactory.getLogger(ScottRestController.class);
@RequestMapping("scott/dept/*")
public class ScottRestController {
	
	@Autowired
	private DeptMapper deptMapper;
	
	@PostMapping("/new")
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dto){ // json ��ü�� java Ŭ���� ��ü�� �ٲ���
		log.info("ScottRestController.insertDept() ........");
		int insertResult = this.deptMapper.insertDept(dto);
		
		//������� ���°� ���� �ѱ��������
		return insertResult==1
				? new ResponseEntity<>("SUCCESS",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
					;
	}
	
	//localhost/scott/dept/50+delete ��û
	//localhost/scott/dept/50+GET ��û scott/dept?deptno=50
	
	@DeleteMapping(value = "/{deptno}")
	public ResponseEntity<String> deleteDept(@PathVariable("deptno")int deptno){
		log.info("ScottRestController.deleteDept() ........"+deptno);
		int deleteResult = this.deptMapper.deleteDept(deptno);
		return deleteResult==1
				? new ResponseEntity<>("SUCCESS",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
					;
		
	}
	
	
	
}//class
