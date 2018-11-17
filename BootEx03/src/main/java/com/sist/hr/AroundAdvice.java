package com.sist.hr;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {
	
	Logger log = Logger.getLogger(this.getClass());
	
	public Object perforLog(ProceedingJoinPoint pjp) throws Throwable {
		Object obj = null;
		
		// 시간측정 start
		String method = pjp.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		try {
			// 메소드 수행
			obj = pjp.proceed();
		} catch (Throwable e) {
			log.debug("=================");
			log.debug(e);
			log.debug("=================");
			throw e;
		}
		
		// 시간 측정 stop
		stopWatch.stop();
		
		log.debug("=================");
		log.debug(method + "()메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis());
		log.debug("=================");
		
		return obj;
	}
}
