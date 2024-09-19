package com.sb.SBdemo.Service;

import com.sb.SBdemo.Entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    // create
    ResponseEntity<Student> create(Student student);

    // create All
    ResponseEntity<List<Student>> createAll(List<Student> students);

    // get All
    ResponseEntity<List<Student>> getAll();

    // get with id
    ResponseEntity<Student> get(int studentId);

    // get with name
    ResponseEntity<Student> getByName(String name);

    // update
    ResponseEntity<ApiResponse> update(Student student);

    // delete
    ResponseEntity<ApiResponse> remove(int studentId);
}
