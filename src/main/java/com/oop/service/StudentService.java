package com.oop.service;

import com.oop.entity.Student;
import com.oop.payload.StudentDto;
import com.oop.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public StudentDto addStudent(StudentDto dto) {
        Student student = MapToEntity(dto);
        studentRepository.save(student);
        StudentDto studentDto = MapToDto(student);
        return studentDto;
    }

    StudentDto MapToDto(Student student) {
        StudentDto dto = modelMapper.map(student, StudentDto.class);
        return dto;
    }

    Student MapToEntity(StudentDto dto) {
        Student student = modelMapper.map(dto, Student.class);
        return student;
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
    public StudentDto updateStudent(long id, StudentDto dto) {
        Student student = MapToEntity(dto);
        student.setId(id);
        Student updateEmployee = studentRepository.save(student);
        StudentDto studentDto = MapToDto(updateEmployee);
        return studentDto;
    }

    public List<StudentDto> getStudent(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> all = studentRepository.findAll(page);
        List<Student> students = all.getContent();
        List<StudentDto> dto = students.stream().map(e -> MapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    public StudentDto getStudentById(long studId) {
        Optional<Student> opStud = studentRepository.findById(studId);
        Student student = opStud.get();
        return MapToDto(student);
    }
}