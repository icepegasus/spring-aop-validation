package com.cos.person.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommonDto<T> {
	
	@NonNull
	private int statusCode;
	private T data;
	
}
