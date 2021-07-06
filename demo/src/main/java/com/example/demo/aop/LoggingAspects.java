package com.example.demo.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspects.class);

	@Before("within(com.example.demo.controller.UserController)")
	public void controllerStartLog(JoinPoint joinPoint) {
		String string = joinPoint.toString();
		String args = Arrays.toString(joinPoint.getArgs());
		logger.info("Start {}, args: {}", string, args);
	}

	@AfterReturning("within(com.example.demo.controller.UserController)")
	public void controllerEndLog(JoinPoint joinPoint) {
		String string = joinPoint.toString();
		String args = Arrays.toString(joinPoint.getArgs());
		logger.info("End {}, args: {}", string, args);
	}
}