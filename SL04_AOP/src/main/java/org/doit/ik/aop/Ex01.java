package org.doit.ik.aop;

public class Ex01 {

	public static void main(String[] args) {
		//System.out.println("hello World");
		// p 204 ������ AOP
		
		// 1. Calculator �������̽� + - * /
		// 2. CalculatorImpl Ŭ���� ����
		// 3. Ex01.java
		CalculatorImpl calc = new CalculatorImpl();
		
		System.out.println(calc.add(4, 2));
		System.out.println(calc.sub(4, 2));
		System.out.println(calc.mult(418951, 8928182));
		System.out.println(calc.div(4, 2));
		
		System.out.println("End");

		
		
		
	}//main
	
}//class
