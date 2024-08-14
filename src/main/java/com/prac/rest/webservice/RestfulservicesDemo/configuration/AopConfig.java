package com.prac.rest.webservice.restfulservicesdemo.configuration;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopConfig {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@After("com.prac.rest.webservice.restfulservicesdemo.service.utility.PointCutUtility.controllerPointCut()")
	public void afterController(JoinPoint joinPoint) {
		logger.info("The {} controller method ends with arguments {}.", joinPoint, joinPoint.getArgs());
	}
	
	@Before("com.prac.rest.webservice.restfulservicesdemo.service.utility.PointCutUtility.servicePointCut()")
	public void beforeService(JoinPoint joinPoint) {
		logger.info("The {} service method starts with arguments {}.", joinPoint, joinPoint.getArgs());
	}
	
	@After("com.prac.rest.webservice.restfulservicesdemo.service.utility.PointCutUtility.servicePointCut()")
	public void afterService(JoinPoint joinPoint) {
		logger.info("The {} service method ends with arguments {}.", joinPoint, joinPoint.getArgs());
	}
	
	@Around("com.prac.rest.webservice.restfulservicesdemo.service.utility.PointCutUtility.aroundPointCut()")
	public Object aroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("The {} repository method starts with arguments {}.", joinPoint, joinPoint.getArgs());
		LocalDateTime startTime = LocalDateTime.now();
		Object returnValue = joinPoint.proceed();
		LocalDateTime endTime = LocalDateTime.now();
		logger.info("The {} method took {} milliseconds to complete.", joinPoint
				, endTime.toInstant(ZoneOffset.UTC).toEpochMilli() - startTime.toInstant(ZoneOffset.UTC).toEpochMilli());
		logger.info("The {} repository method ends with arguments {}.", joinPoint, joinPoint.getArgs());
		return returnValue;
	}
	
}
