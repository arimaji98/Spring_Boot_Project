package com.sb.SBdemo.Controller;

import com.sb.SBdemo.Entity.Student;
import com.sb.SBdemo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // http://localhost:8081/student/add
    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PostMapping("/addStudents")
    public ResponseEntity<List<Student>> addStudent(@RequestBody List<Student> students) {
        return studentService.createAll(students);
    }

    // http://localhost:8081/student/get
    @GetMapping("/getStudents")
    public ResponseEntity<List<Student>> getStudents() {
        return studentService.getAll();
    }

    // BY @PathVariable => http://localhost:8081/student/getStudent/3
    @GetMapping("/getStudent/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable int studentId) {
        return studentService.get(studentId);
    }

    // BY @RequestParam => http://localhost:8081/student/getStudent?studentID=3
    @GetMapping("/getStudent")
    public ResponseEntity<Student> getStudentByReqParam(@RequestParam int studentID) {
        return studentService.get(studentID);
    }

    @GetMapping("/getStudentByName/{studentName}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String studentName) {
        return studentService.getByName(studentName);
    }

    @PutMapping("/update")
    public  ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return studentService.update(student);
    }

    // http://localhost:8081/student/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeStudent(@PathVariable Integer id) {
        return studentService.remove(id);
    }
}
