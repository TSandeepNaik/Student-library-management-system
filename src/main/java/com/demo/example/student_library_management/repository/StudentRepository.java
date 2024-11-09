package com.demo.example.student_library_management.repository;

import com.demo.example.student_library_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//here it extends the Jpa repository and it will take Student class and datatype of primary key
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
