package com.sb.SBdemo.Service;

import com.sb.SBdemo.Dto.StudentDto;
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
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<StudentDto> create(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        studentRepo.save(student);
        return new ResponseEntity("STUDENT DATA SAVED SUCCESSFULLY.", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<StudentDto>> createAll(List<StudentDto> studentDtoList) {
        List<Student> students = studentDtoList.stream()
                                               .map(dto -> modelMapper.map(dto, Student.class))
                                               .collect(Collectors.toList());
        studentRepo.saveAll(students);
        return new ResponseEntity("ALL STUDENTS DATA SAVED SUCCESSFULLY.", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<StudentDto>> getAll() {
        List<StudentDto> studentList = studentRepo.findAll().stream()
                                                  .map(dto -> modelMapper.map(dto, StudentDto.class))
                                                  .collect(Collectors.toList());
        if (studentList.isEmpty()) {
            return new ResponseEntity("STUDENTS RECORD NOT FOUND", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.of(Optional.of(studentList));
    }

    @Override
    public ResponseEntity<StudentDto> get(int studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new NoSuchElementExceptionHandler("Student"
                , "ID", studentId));
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return ResponseEntity.of(Optional.of(studentDto));
    }

    @Override
    public ResponseEntity<StudentDto> getByName(String name) {
        Student studentName = studentRepo.findByName(name);
        if (studentName == null) {
            throw new NoSuchElementExceptionHandler("Student", "name", name);
        }
        StudentDto studentDto = modelMapper.map(studentName, StudentDto.class);
        return ResponseEntity.of(Optional.of(studentDto));
    }

    @Override
    public ResponseEntity<ApiResponse> update(StudentDto studentDto) {
        Student id = studentRepo.findById(studentDto.getId()).orElseThrow(() -> new NoSuchElementExceptionHandler(
                "Student", "ID", studentDto.getId()));

        Student updatedStudent = modelMapper.map(studentDto, Student.class);
        studentRepo.save(updatedStudent);
        return new ResponseEntity(new ApiResponse("Student record with ID " + updatedStudent.getId() + " updated " +
                                                  "successfully", true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse> remove(int studentId) {
        Student id = studentRepo.findById(studentId).orElseThrow(() -> new NoSuchElementExceptionHandler("Student"
                , "ID", studentId));
        studentRepo.deleteById(studentId);
        return new ResponseEntity(new ApiResponse("Student record with ID " + studentId + " deleted successfully",
                                                  true), HttpStatus.OK);
    }
}
