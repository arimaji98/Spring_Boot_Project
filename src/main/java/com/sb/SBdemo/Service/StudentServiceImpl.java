package com.sb.SBdemo.Service;

import com.sb.SBdemo.Entity.Student;
import com.sb.SBdemo.Exceptions.NoSuchElementExceptionHandler;
import com.sb.SBdemo.Repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Student> create(Student student) {
        studentRepo.save(student);
        return new ResponseEntity("STUDENT DATA SAVED SUCCESSFULLY.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Student>> createAll(List<Student> students) {
        studentRepo.saveAll(students);
        return new ResponseEntity("ALL STUDENTS DATA SAVED SUCCESSFULLY.", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentRepo.findAll();
        if (studentList.isEmpty()) {
            return new ResponseEntity("STUDENTS RECORD NOT FOUND", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.of(Optional.of(studentList));
    }

    @Override
    public ResponseEntity<Student> get(int studentId) {
        Student id = studentRepo.findById(studentId).orElseThrow(() -> new NoSuchElementExceptionHandler("Student", "ID", studentId));
        return ResponseEntity.of(Optional.of(id));
    }

    @Override
    public ResponseEntity<Student> getByName(String name) {
        Student studentName = studentRepo.findByName(name);
        if (studentName == null) {
            throw new NoSuchElementExceptionHandler("Student", "name", name);
        }
        return ResponseEntity.of(Optional.of(studentName));
    }

    @Override
    public ResponseEntity<ApiResponse> update(Student student) {
        Student id = studentRepo.findById(student.getId()).orElseThrow(() -> new NoSuchElementExceptionHandler("Student", "ID", student.getId()));

        Student stdnt = modelMapper.map(student, Student.class);
        studentRepo.save(stdnt);
        return new ResponseEntity(new ApiResponse("Student record with ID " + student.getId() + " updated successfully", true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> remove(int studentId) {
        Student id = studentRepo.findById(studentId).orElseThrow(() -> new NoSuchElementExceptionHandler("Student", "ID", studentId));
        studentRepo.deleteById(studentId);
        return new ResponseEntity(new ApiResponse("Student record with ID " + studentId + " deleted successfully", true), HttpStatus.OK);
    }
}
