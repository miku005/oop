package com.oop.controller;

import com.oop.payload.StudentDto;
import com.oop.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    //http://localhost:8081/student/add
    @PostMapping("/add")
    public ResponseEntity<?> addStudent(
            @Valid @RequestBody StudentDto dto, BindingResult result){
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    result.getFieldError()
                            .getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        StudentDto studentDto = studentService.addStudent(dto);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }
    //http://localhost:8081/student?id=2
    @DeleteMapping
    public ResponseEntity<String> deleteStudent(
            @RequestParam long id){
        studentService.deleteStudent(id);
        return  new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    //http://localhost:8081/student?id=4
@PutMapping
    public ResponseEntity<StudentDto> updateStudent(
            @RequestParam long id,@RequestBody StudentDto dto){
        StudentDto studentDto = studentService.updateStudent(id, dto);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }
    //http://localhost:8081/student?pageNo=0&pageSize=3&sortBy=name&sortDir=desc
   @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(
            @RequestParam(name="pageNo",required = false,defaultValue = "0")int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue = "3")int pageSize,
            @RequestParam(name="sortBy",required = false,defaultValue = "name")String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue = "asc")String sortDir
            ){
        List<StudentDto> studentDto = studentService.getStudent(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }
    //http://localhost:8081/student/studentId/2
    @GetMapping("/studentId/{studId}")
    public ResponseEntity<StudentDto> getStudentsById(
            @PathVariable long studId
    ){
        StudentDto studentDto = studentService.getStudentById(studId);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }
}
