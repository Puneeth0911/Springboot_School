package com.alpha.springschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.springschool.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleStudentNotFoundException() {
		ResponseStructure<String> responsestructure=new ResponseStructure<String>();
		responsestructure.setStatuscode(HttpStatus.NOT_FOUND.value());
		responsestructure.setMessage("Student not found");
		responsestructure.setData("Student not found");
		return new ResponseEntity<ResponseStructure<String>>(responsestructure,HttpStatus.NOT_FOUND);
	}
}