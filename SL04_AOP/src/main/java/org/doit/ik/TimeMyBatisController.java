package org.doit.ik;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.mapper.TimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class TimeMyBatisController {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeMyBatisController.class);
	
	@Autowired //Dependency injection 의존성 주입.
	private TimeMapper timeMapper;
	

	
	//@RequestMapping(value = "/time", method = RequestMethod.GET)
	@GetMapping(value = "/time")//위에 열할과 동일.
	
	public String time(Locale locale, Model model, HttpServletRequest request) {
		logger.info(">TimeMyBatisController.time()...");
		
		String currentTime =  this.timeMapper.getTime();
		
		
		model.addAttribute("currentTime",currentTime);
		request.setAttribute("currentTime2", currentTime);
		
		String currentNextTime = this.timeMapper.getNextTime();
		model.addAttribute("currentNextTime",currentNextTime);
		
		
		
		return "time"; //WEB-INF/view/time.jsp
	}
	
}
