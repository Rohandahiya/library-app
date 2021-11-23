package com.example.gfg.libraryapp.controller;

import com.example.gfg.libraryapp.models.Student;
import com.example.gfg.libraryapp.requests.StudentRequest;
import com.example.gfg.libraryapp.requests.StudentUpdateRequest;
import com.example.gfg.libraryapp.security.User;
import com.example.gfg.libraryapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/student")
    public Student getStudent(){
        // This authentication object contains the details related to a logged in user like username password and other fields
        Authentication authentication = (Authentication)SecurityContextHolder.getContext().getAuthentication();

        User user = (User)authentication.getPrincipal();

        return studentService.getStudent(user.getStudent().getId());
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

    @GetMapping("/student/create")
    public void createStudent(@RequestBody StudentRequest studentRequest){
         studentService.createStudent(studentRequest);
    }

    @PutMapping("/student")
    public void updateStudent(@RequestBody StudentUpdateRequest studentUpdateRequest){

        Authentication authentication = (Authentication)SecurityContextHolder.getContext().getAuthentication();

        User user = (User)authentication.getPrincipal();

        studentService.updateStudent(user.getStudent().getId(),studentUpdateRequest);
    }

    @DeleteMapping("/student/{student_id}")
    public void deleteStudent(@PathVariable("student_id") int id){
        studentService.deleteStudent(id);
    }
}
