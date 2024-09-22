package com.sb.SBdemo.Controller;

import com.sb.SBdemo.Dto.StudentDto;
import com.sb.SBdemo.Service.ApiResponse;
import com.sb.SBdemo.Service.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    // http://localhost:8081/student/add
    @PostMapping("/addStudent")
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.create(studentDto);
    }

    @PostMapping("/addStudents")
    public ResponseEntity<List<StudentDto>> addStudents(@Valid @RequestBody List<StudentDto> studentsDto) {
        return studentService.createAll(studentsDto);
    }

    // http://localhost:8081/student/get
    @GetMapping("/getStudents")
    public ResponseEntity<List<StudentDto>> getStudents() {
        return studentService.getAll();
    }

    // BY @PathVariable => http://localhost:8081/student/getStudent/3
    @GetMapping("/getStudent/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable int studentId) {
        return studentService.get(studentId);
    }

    // BY @RequestParam => http://localhost:8081/student/getStudent?studentID=3
    @GetMapping("/getStudent")
    public ResponseEntity<StudentDto> getStudentByReqParam(@RequestParam int studentID) {
        return studentService.get(studentID);
    }

    @GetMapping("/getStudentByName/{studentName}")
    public ResponseEntity<StudentDto> getStudentByName(@PathVariable String studentName) {
        return studentService.getByName(studentName);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.update(studentDto);
    }

    // http://localhost:8081/student/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> removeStudent(@PathVariable Integer id) {
        return studentService.remove(id);
    }
}
