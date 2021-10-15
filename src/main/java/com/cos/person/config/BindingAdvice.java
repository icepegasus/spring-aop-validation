package com.cos.person.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import com.cos.person.domain.CommonDto;

@Component
@Aspect
public class BindingAdvice {
	
	@Before("execution(* com.cos.person.web..*Controller.*(..))")
	public void testCheck() {
		
		HttpServletRequest request = 
		((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		System.out.println("주소 : "+request.getRequestURI());
		
		System.out.println("로그를 남겼습니다.");
	}
	
	//@Before
	//@After
	@Around("execution(* com.cos.person.web..*Controller.*(..))")
	public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		
		System.out.println("type : "+type);
		System.out.println("method : "+method);
		
		Object[] args = proceedingJoinPoint.getArgs();
		
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				
				if(bindingResult.hasErrors()) {
					Map<String,String> errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					return new CommonDto<>(HttpStatus.BAD_REQUEST.value(),errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed();  //aop 로 낚아챈 method 다시 실행
	}
}
