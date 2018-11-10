package com.sist.hr;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class LoggingAOP {

	Logger log = Logger.getLogger(LoggingAOP.class);
	
	// JoinPoint : 메소드의 param, 메소드의 이름.
	public void logging(JoinPoint joinPoint) {
		Signature method = joinPoint.getSignature();
		String methodName = method.getName();
		
		log.debug(">>>>>=================");
		log.debug("methodName -> " + methodName + "is calling...!");
		log.debug(">>>>>=================");
	}
	
}
