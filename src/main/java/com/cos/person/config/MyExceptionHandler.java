package com.cos.person.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.CommonDto;

// Controller 단 Exception을 낚아채기
@RestController
@ControllerAdvice
public class MyExceptionHandler {
	
//	@ExceptionHandler(value=IllegalArgumentException.class)
//	public String argumentException(IllegalArgumentException e) {
//		return "오류 : "+e.getMessage();
//	}
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public CommonDto<String> argumentException(IllegalArgumentException e) {
		return new CommonDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
	

}
