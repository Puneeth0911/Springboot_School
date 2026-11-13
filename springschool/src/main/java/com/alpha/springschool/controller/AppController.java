package com.alpha.springschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.PatchExchange;

import com.alpha.springschool.dto.ResponseStructure;
import com.alpha.springschool.entity.Student;
import com.alpha.springschool.service.StudentService;

@RestController
public class AppController {
	
	@GetMapping("/demo/name/{name}/age/{age}")
	public void demo(@PathVariable String name,@PathVariable int age) {
//		http://localhost:8085/demo/name/Puneeth/age/21
		System.out.println("This is Demo Method");
		System.out.println(name);
		System.out.println(age);
	}
	
	@GetMapping("/test")
	public void test(@RequestParam String name,@RequestParam int age) {
//		http://localhost:8085/test?name=Unknown&age=22
		System.out.println("This is Test Method");
		System.out.println(name);
		System.out.println(age);
	}
	
	@PostMapping("/sample")
	public void sample(@RequestHeader String name,@RequestHeader int age) {
		System.out.println("This is Sample req");
		System.out.println(name);
		System.out.println(age);
	}
	
	@Autowired
	private StudentService studentservice;
	@PostMapping("/registerstudent")
	public ResponseEntity<ResponseStructure<Student>> regStudent(@RequestBody Student s) {
//		{ "id":101,  "name":"Balu",  "age":20,  "mobno":9}
		System.out.println("Reg Student");
		return studentservice.regStudent(s);
	}
	
	@GetMapping("/findstudent")
	public ResponseEntity<ResponseStructure<Student>> findstudent(@RequestParam int id) {
//		http://localhost:8085/findstudent?id=101
		System.out.println("Finding student with id "+id);
		return studentservice.findStudent(id);
	}
	
	@PatchMapping("/updatestudent")
//	http://localhost:8085/updatestudent?id=101&newmobno=123456
	public ResponseEntity<ResponseStructure<Student>> updatestudent(@RequestParam int id,@RequestParam long newmobno) {
		return studentservice.updateStudent(id,newmobno);
	}
	
	@DeleteMapping("/deletestudent")
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(@RequestParam int id) {
//		http://localhost:8085/deletestudent?id=101
		return studentservice.deleteStudent(id);
	}
}
