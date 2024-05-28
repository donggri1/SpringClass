package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

//@Component
public class LogPrintBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, // add()
			Object[] args, //3,5 �Ű�����
			Object target // ���Ͻþȿ��� �ٽ� ����ϴ� ���� ��ü
			) throws Throwable {
		
		String methodName = method.getName();
		Log log = LogFactory.getLog(getClass());
		
		log.info(">> " + methodName + "() : LogPrintBeforeAdvice 호출됨...");
		
		
		
	}//before

}
