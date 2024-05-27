package org.doit.ik;

import org.doit.ik.service.ProductImgService;
import org.doit.ik.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/product/*")
@Log4j
@AllArgsConstructor
public class ProductController {

	private ProductService productService;
	private ProductImgService productImgService;
	
    @GetMapping(value = {"/view"})
    public String get(Model model, @RequestParam("productid") long id) {
        log.info("> 컨트롤러 view");
        model.addAttribute("product", this.productService.get(id));
		
		   return "product/view";
	}//views
	

}//class

