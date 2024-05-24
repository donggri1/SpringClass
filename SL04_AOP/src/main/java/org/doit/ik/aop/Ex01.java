package org.doit.ik.aop;

public class Ex01 {

	public static void main(String[] args) {
		//System.out.println("hello World");
		// p 204 스프링 AOP
		
		// 1. Calculator 인터페이스 + - * /
		// 2. CalculatorImpl 클래스 구현
		// 3. Ex01.java
		CalculatorImpl calc = new CalculatorImpl();
		
		System.out.println(calc.add(4, 2));
		System.out.println(calc.sub(4, 2));
		System.out.println(calc.mult(418951, 8928182));
		System.out.println(calc.div(4, 2));
		
		System.out.println("End");

		
		
		
	}//main
	
}//class
