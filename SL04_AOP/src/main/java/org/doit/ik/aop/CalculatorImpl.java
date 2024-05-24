package org.doit.ik.aop;

public class CalculatorImpl implements Calculator{

	@Override
	public int add(int x, int y) {
		
		long start = System.nanoTime();
		int result = x+y;
		long end= System.nanoTime();
		System.out.printf("> µ¡¼À Ã³¸®½Ã°£ : %d ns\n",(end+start));
		
		return result;
	}

	@Override
	public int sub(int x, int y) {
		long start = System.nanoTime();
		int result = x-y;
		long end= System.nanoTime();
		System.out.printf("> »©±â Ã³¸®½Ã°£ : %d ns\n",(end-start));
		
		return result;
	}

	@Override
	public int mult(int x, int y) {
		long start = System.nanoTime();
		int result = x*y;
		long end= System.nanoTime();
		System.out.printf("> °öÇÏ±â Ã³¸®½Ã°£ : %d ns\n",(end*start));
		
		return result;
	}

	@Override
	public int div(int x, int y) {
		long start = System.nanoTime();
		int result = x/y;
		long end= System.nanoTime();
		System.out.printf("> ³ª´«¼À Ã³¸®½Ã°£ : %d ns\n",(end/start));
		
		return result;
	}

}
