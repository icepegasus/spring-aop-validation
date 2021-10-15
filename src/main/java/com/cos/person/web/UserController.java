package com.cos.person.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.person.domain.CommonDto;
import com.cos.person.domain.JoinReqDto;
import com.cos.person.domain.UpdateReqDto;
import com.cos.person.domain.User;
import com.cos.person.domain.UserRepository;

@RestController
public class UserController {
	
	private UserRepository userRepository;
	
	//생성자 DI
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/user")
	public CommonDto<List<User>> findAll() {
		
		System.out.println("findAll()"); 
		return new CommonDto<>(HttpStatus.OK.value(),userRepository.findAll()); //MessageConverter (JavaObject -> Json String)
	}
	
	@GetMapping("/user/{id}")
	public CommonDto<User> findById(@PathVariable int id) {
		System.out.println("findById() : id : "+id);
		
		return new CommonDto<>(HttpStatus.OK.value(), userRepository.findById(id));
		
	}
	
	@CrossOrigin //자바스크립트로 요청하는 것을 기본적으로 허용을 안하는데, 해당 어노테이션으로 요청허용
	@PostMapping("/user")
	//x-www--form-urlencoded => request.getParameter()
	//text/plain => RequestBody 어노테이션으로 받을 수 있음
	//application/json => RequestBody로 json을 java Object로 받을 수 있음
	public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String,String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new CommonDto<>(HttpStatus.BAD_REQUEST.value(),errorMap);
		}
		
		System.out.println("save()");
		System.out.println("dto : "+dto);
		
		userRepository.save(dto);
		
		return new CommonDto<>(HttpStatus.CREATED.value(),"ok");
//		System.out.println("data : "+data);
//		System.out.println("username : "+username);
//		System.out.println("password : "+password);
//		System.out.println("phone : "+phone);
	}
	
	@DeleteMapping("/user/{id}")
	public CommonDto delete(@PathVariable int id) {
		System.out.println("delete()");
		
		return new CommonDto<>(HttpStatus.OK.value());
	}
	
	@PutMapping("/user/{id}")
	public CommonDto<?> update(@PathVariable int id, @Valid @RequestBody UpdateReqDto dto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String,String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new CommonDto<>(HttpStatus.BAD_REQUEST.value(),errorMap);
		}
		
		System.out.println("update()");
		System.out.println("id : "+id+ ",dto : "+dto);
		
		userRepository.update(id, dto);
		return new CommonDto<>(HttpStatus.OK.value());
	}

}
