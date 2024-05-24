package org.doit.ik.aop2.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;


public class LogPrintAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start();
		
		String methodName =method.getMethod().getName();
		Log log = LogFactory.getLog(this.getClass());
		log.info("> " + methodName + "() start." );
		
		Object result =  method.proceed();// 핵심 기능 
		
		log.info("> " + methodName + "() end." );
		sw.stop();
		log.info("> " + methodName + "() 처리시간 : " + sw.getTotalTimeMillis()+"ms" );
		return result;
	}

}
