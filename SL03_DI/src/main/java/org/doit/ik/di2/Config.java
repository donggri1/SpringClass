package org.doit.ik.di2;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//application-context.xml === 자바클래스 설정 파일
@Configuration
public class Config {
	
	//RecordImpl record = new RecordImpl();
	//<bean id="record" class="org.doit.ik.di.RecordImpl"></bean>
	
	@Bean
	public RecordImpl record(){
		
		
		return new RecordImpl();
	}
	/*
	 * <bean id="rvi" class="org.doit.ik.di.RecordViewImpl"> <!-- <property
	 * name="record" ref="record"></property> set --> <property name="record"> <ref
	 * bean="record"/> </property> <!-- <constructor-arg ref="recod"/> --> </bean>
	 */
	
	@Bean(name = "rvi")
	public RecordViewImpl getRecordViewImpl() {
		
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record());
		
		return rvi;
		
	}
	
}//class
