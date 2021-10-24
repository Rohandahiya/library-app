package com.example.gfg.libraryapp.controller;

import com.example.gfg.libraryapp.models.Student;
import com.example.gfg.libraryapp.requests.StudentRequest;
import com.example.gfg.libraryapp.requests.StudentUpdateRequest;
import com.example.gfg.libraryapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable("id") int id){
        return studentService.getStudent(id);
    }

    @GetMapping("/students/all")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/students/jpql/age/{age}")
    public List<Student> getStudentsByAgeJPQL(@PathVariable("age") int age){
        return studentService.getStudentForAgeJPQL(age);
    }

    @GetMapping("/students/age/{age}")
    public List<Student> getStudentsByAgeNativeQuery(@PathVariable("age") int age){
        return studentService.getStudentForAgeNativeQuery(age);
    }

    @PostMapping("/student")
    public void createStudent(@RequestBody StudentRequest studentRequest){
         studentService.createStudent(studentRequest);
    }

    @PutMapping("/student/{id}")
    public void updateStudent(@PathVariable("id") int id,@RequestBody StudentUpdateRequest studentUpdateRequest){
         studentService.updateStudent(id,studentUpdateRequest);
    }

    @DeleteMapping("/student/{student_id}")
    public void deleteStudent(@PathVariable("student_id") int id){
        studentService.deleteStudent(id);
    }
}
