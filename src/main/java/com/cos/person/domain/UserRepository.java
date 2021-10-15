package com.cos.person.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		
		users.add(new User(1,"ssar","1234","010222"));
		users.add(new User(1,"cos","1234","010222"));
		users.add(new User(1,"love","1234","010222"));
		
		return users;
	}
	
	public User findById(int id){
		List<User> users = new ArrayList<>();
		
		return new User(1,"ssar","1234","010222");
		
	}
	
	public void save(JoinReqDto dto) {
		System.out.println("DB에 insert하기");
	}
	
	public void delete(int id) {
		System.out.println("DB에 삭제하기");
	}
	
	public void update(int id, UpdateReqDto dto) {
		
		throw new IllegalArgumentException("잘못된 아규먼트 오류발생");
		//System.out.println("DB에 업데이트하기");
	}
	

}
