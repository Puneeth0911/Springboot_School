package com.alpha.springschool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.springschool.dto.ResponseStructure;
import com.alpha.springschool.entity.Student;
import com.alpha.springschool.exception.StudentNotFoundException;
import com.alpha.springschool.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentrepository;

	public ResponseEntity<ResponseStructure<Student>> regStudent(Student s) {
		ResponseStructure<Student> rs = new ResponseStructure<Student>();
		if (studentrepository.findById(s.getId()).isEmpty()) {
			Student studentsaved = studentrepository.save(s);
			rs.setStatuscode(HttpStatus.CREATED.value());
			rs.setMessage("Student registered successfully");
			rs.setData(studentsaved);
			return new ResponseEntity<ResponseStructure<Student>>(rs, HttpStatus.CREATED);
		}
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("Student already present with id: " + s.getId());
		rs.setData(null);
		return new ResponseEntity<ResponseStructure<Student>>(rs, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Student>> findStudent(int id) {
//		Optional<Student> stuopt=studentrepository.findById(id);
		Student student = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
		ResponseStructure<Student> rs = new ResponseStructure<Student>();
//		if(stuopt.isPresent()) {
//			Student foundstudent=stuopt.get();
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("Student Found");
		rs.setData(student);
		return new ResponseEntity<ResponseStructure<Student>>(rs, HttpStatus.FOUND);
//		} else {
//			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
//			rs.setMessage("Student Not Found");
//			rs.setData(null);
//			return new ResponseEntity<ResponseStructure<Student>>(rs,HttpStatus.NOT_FOUND);
//		}
	}

	public ResponseEntity<ResponseStructure<Student>> updateStudent(int id, long newmobno) {
		Student student = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
		ResponseStructure<Student> rs = new ResponseStructure<Student>();
//		Student s = student.get();
		student.setMobno(newmobno);
		studentrepository.save(student);
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("Student Found");
		rs.setData(student);
		return new ResponseEntity<ResponseStructure<Student>>(rs, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Student>> deleteStudent(int id) {
		Student student = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
		ResponseStructure<Student> rs = new ResponseStructure<Student>();
		studentrepository.delete(student);
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("Student Found");
		rs.setData(student);
		return new ResponseEntity<ResponseStructure<Student>>(rs, HttpStatus.FOUND);
	}

}
