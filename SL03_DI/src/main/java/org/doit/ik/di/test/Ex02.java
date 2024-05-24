package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
		
		//String resourceLocations = "classpath:org/doit/ik/di/application-context.xml";
		String resourceLocations = "application-context.xml";
		GenericXmlApplicationContext ctx = new 
				GenericXmlApplicationContext(resourceLocations);
		
		//RecordViewImpl rvi = (RecordViewImpl)ctx.getBean("rvi");
		RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class);
		rvi.input();
		rvi.output();
		
		System.out.println(" end ");
		
		
	}	//main

}//class
