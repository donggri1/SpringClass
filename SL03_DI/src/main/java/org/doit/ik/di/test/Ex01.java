package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {

	public static void main(String[] args) {
		//System.out.println( " Hello World! ");
		RecordImpl record = new RecordImpl();
		//RecordViewImpl rvi = new RecordViewImpl(record);
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record); // ������ ������Ƽ(property) ���
		
		rvi.input();
		rvi.output();
		
		System.out.println(" end ");
		
		
	}	//main

}//class
