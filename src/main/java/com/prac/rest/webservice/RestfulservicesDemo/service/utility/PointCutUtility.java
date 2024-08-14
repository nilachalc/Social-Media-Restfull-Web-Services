package com.prac.rest.webservice.restfulservicesdemo.service.utility;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutUtility {

	@Pointcut("execution(* com.prac.rest.webservice.restfulservicesdemo.restendpoint.*.*(..))")
	public void controllerPointCut() {}
	
	@Pointcut("execution(* com.prac.rest.webservice.restfulservicesdemo.service.*.*(..))")
	public void servicePointCut() {}
	
	@Pointcut("@annotation(com.prac.rest.webservice.restfulservicesdemo.service.utility.TrackExecutionTime)")
	public void aroundPointCut() {}
}
