package org.doit.ik.aop5;

import org.doit.ik.aop.Calculator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("calc5")
public class CalculatorImpl5 implements Calculator{

	@Override
	public int add(int x, int y) {
		int result = x+y;

		return result;
	}

	@Override
	public int sub(int x, int y) {
		int result = x-y;

		return result;
	}

	@Override
	public int mult(int x, int y) {
		int result = x*y;
		
		return result;
	}

	@Override
	public int div(int x, int y) {
		int result = x/y;
		
		return result;
	}

}
