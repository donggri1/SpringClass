package org.doit.ik.aop5;

import org.doit.ik.aop.Calculator;
import org.doit.ik.aop.CalculatorImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:org/doit/ik/aop5/application-context5.xml");
		
		Calculator calc = ctx.getBean("calc5", Calculator.class);
		
		System.out.println( calc.sub(3,5));
		System.out.println("end");
		
	}//main
	
	
}//class
