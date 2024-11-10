package com.demo.example.student_library_management.controller;

import com.demo.example.student_library_management.converters.StudentConverter;
import com.demo.example.student_library_management.dto.StudentRequestDto;
import com.demo.example.student_library_management.model.Student;
import com.demo.example.student_library_management.service.StudentServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/api")
public class StudentController {

    @Autowired  //we are creating the object for the student service class to use the savestudent fun and write the api
    private StudentServiece studentServiece;

    @PostMapping("/save")  // to save the student data we use post mapping
    public  String saveStudent(@RequestBody StudentRequestDto studentRequestDto){
         String response = studentServiece.addStudent(studentRequestDto);
         return response;
    }
}
