package com.sist.hr;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {

	private Logger log=Logger.getLogger(this.getClass());
	
	public Object performLog(ProceedingJoinPoint pjp)throws Throwable{
		Object obj=null;
		String method = pjp.getSignature().getName();
		//----------------------------------------
		//-1.시간 측정 Start
		//----------------------------------------		
		StopWatch stopWatch=new StopWatch();
		stopWatch.start();

		//----------------------------------------
		//-2.method run
		//----------------------------------------			
		try {
			
			obj = pjp.proceed();
		}catch(Throwable e) {
			log.debug("===============");
			log.debug(e.getMessage());
			log.debug("===============");
			throw e;
		}
		//----------------------------------------
		//-3.시간 측정 stop
		//----------------------------------------			
		stopWatch.stop();
		log.debug("===============");
		log.debug(method+"()메소드 수행에 걸린 시간:"+stopWatch.getTotalTimeMillis()+"(ms)초");
		log.debug("===============");
		return obj;
	}
	
	
}
