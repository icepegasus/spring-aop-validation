package com.cos.person.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BindingAdvice {
	
	
	//@Before
	//@After
	@Around("execution(* com.cos.person.web..*Controller.*(..))")
	public void validCheck(ProceedingJoinPoint proceedingJoinPoint) {
		
	}

}
