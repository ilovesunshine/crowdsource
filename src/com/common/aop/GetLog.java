package com.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
@Service("logInterceptor")
public class GetLog {

	@Pointcut("execution(public * com.chinasofti.*.dao..*.*(..))")
	public void myMethod(){
	}
	@Around("myMethod()")
	public void before(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("method before");
		pjp.proceed();
	}

	@AfterReturning("myMethod()")
	public void afterReturning() throws Throwable{
		System.out.println("method afterReturning");
	}

	@After("myMethod()")
	public void afterFinily() throws Throwable{
		System.out.println("method end");
	}
}
