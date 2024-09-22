package com.sb.SBdemo.Service;

import com.sb.SBdemo.Dto.StudentDto;
import com.sb.SBdemo.Entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    // create
    ResponseEntity<StudentDto> create(StudentDto student);

    // create All
    ResponseEntity<List<StudentDto>> createAll(List<StudentDto> students);

    // get All
    ResponseEntity<List<StudentDto>> getAll();

    // get with id
    ResponseEntity<StudentDto> get(int studentId);

    // get with name
    ResponseEntity<StudentDto> getByName(String name);

    // update
    ResponseEntity<ApiResponse> update(StudentDto student);

    // delete
    ResponseEntity<ApiResponse> remove(int studentId);
}
