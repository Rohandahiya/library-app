package com.example.gfg.libraryapp.repository;

import com.example.gfg.libraryapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    //JPQL Way - w.r.t Java class
   // @Query("select s from Student s where s.age> ?1 ") question mark means first argument in the function
    @Query("select s from Student s where s.age> :age")
    public List<Student> getStudentByAgeJPQL(int age);

    // Native Query Way - w.r.t Database values
    @Query(value = "select * from student where age < :age",nativeQuery = true)
    public List<Student> getStudentByAgeNativeQuery(int age);

    @Transactional
    @Modifying
    @Query("update Student s set s.email=?1, s.name=?2, s.age=?3 where s.id=?4")
    public void update(String email,String name,int age,int id);

}
