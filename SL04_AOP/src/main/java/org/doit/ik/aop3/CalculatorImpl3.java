package org.doit.ik.aop3;

import org.doit.ik.aop.Calculator;
import org.springframework.stereotype.Component;

@Component("calc")
public class CalculatorImpl3 implements Calculator{

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
