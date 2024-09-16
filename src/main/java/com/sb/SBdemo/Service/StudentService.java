package com.sb.SBdemo.Service;

import com.sb.SBdemo.Entity.Student;
import com.sb.SBdemo.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public ResponseEntity<Student> create(Student student) {
        studentRepo.save(student);
        return new ResponseEntity("STUDENT DATA SAVED SUCCESSFULLY.", HttpStatus.OK);
    }

    public ResponseEntity<List<Student>> createAll(List<Student> students) {
        studentRepo.saveAll(students);
        return new ResponseEntity("ALL STUDENTS DATA SAVED SUCCESSFULLY.", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Student>> getAll() {
        List<Student> studentList = studentRepo.findAll();
        if (studentList.isEmpty()) {
            return new ResponseEntity("STUDENTS RECORD NOT FOUND", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.of(Optional.of(studentList));
    }

    public ResponseEntity<Student> get(int studentId) {
        Student id = studentRepo.findById(studentId).orElse(null);
        if (id == null) {
            return new ResponseEntity("STUDENT RECORD NOT FOUND", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.of(Optional.of(id));
    }

    public ResponseEntity<Student> getByName(String name) {
        Student studentName = studentRepo.findByName(name);
        if (studentName == null) {
            return new ResponseEntity("STUDENT RECORD NOT FOUND", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.of(Optional.of(studentName));
    }

    public ResponseEntity<Student> update(Student student) {
        Student stdnt = studentRepo.getReferenceById(student.getId());
        stdnt.setName(student.getName());
        stdnt.setCity(student.getCity());
        studentRepo.save(stdnt);
        return new ResponseEntity("RECORD UPDATED FOR STUDENT ID " + student.getId(), HttpStatus.OK);
    }

    public ResponseEntity<String> remove(int studentId) {
        Student id = studentRepo.findById(studentId).orElse(null);
        if (id == null) {
            return new ResponseEntity("INCORRECT STUDENT ID", HttpStatus.NOT_FOUND);
        }
        studentRepo.deleteById(studentId);
        return new ResponseEntity("STUDENT ID " + studentId + " DELETED.", HttpStatus.OK);
    }
}
