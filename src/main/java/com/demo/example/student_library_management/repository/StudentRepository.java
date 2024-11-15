package com.demo.example.student_library_management.repository;

import com.demo.example.student_library_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//here it extends the Jpa repository and it will take Student class and datatype of primary key
//repository layer helps us to get all the inbuilt functions like adding delete update remove etc.
public interface StudentRepository extends JpaRepository<Student, Integer> {

 //writing your own methods with fields or attributes with the Jpa support


 //hence it is an interface we don't write body for methods and jpa internally create the query for these methods.
   public  Student findByEmail(String email);

   //there can be more student with the same name so when u write for name it will give the list of student as its written type



 //writing owm complete query without Jpa support
 //it is not working                                            here there should be no space
 @Query(nativeQuery = true, value = "SELECT * FROM student where email=:inputEmail")
  public Student searchByEmailQuery(String  inputEmail);

  }

