package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

public class LogPrintAfterReturningAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(
							Object returnValue //
							, Method method // �޼���
							, Object[] args	// �Ű�����
							, Object target	// ���� ��ü
							) throws Throwable {
		
		String methodName = method.getName();
		Log log = LogFactory.getLog(getClass());
		
		log.info("<< " + methodName + "() : LogPrintAfterReturningAdvice 호출됨 ...");
		
		
	}//after

}
