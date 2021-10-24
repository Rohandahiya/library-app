package com.example.gfg.libraryapp.services;

import com.example.gfg.libraryapp.models.Student;
import com.example.gfg.libraryapp.repository.StudentRepository;
import com.example.gfg.libraryapp.requests.StudentRequest;
import com.example.gfg.libraryapp.requests.StudentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getStudent(int id){
       return studentRepository.findById(id).orElse(null);   // find by id only takes the primary key as argument in the function not any other property
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void createStudent(StudentRequest studentRequest){
        Student student = Student
                .builder()
                .name(studentRequest.getName())
                .email(studentRequest.getEmail())
                .age(studentRequest.getAge())
                .build();

        studentRepository.save(student);
    }

    public void updateStudent(int id, StudentUpdateRequest studentUpdateRequest){
         studentRepository.update(studentUpdateRequest.getEmail(),studentUpdateRequest.getName(),studentUpdateRequest.getAge(),id);
    }

    public void deleteStudent(int id){
         studentRepository.deleteById(id);
    }

    public List<Student> getStudentForAgeJPQL(int age){
        return studentRepository.getStudentByAgeJPQL(age);
    }

    public List<Student> getStudentForAgeNativeQuery(int age){
        return  studentRepository.getStudentByAgeNativeQuery(age);
    }
}
