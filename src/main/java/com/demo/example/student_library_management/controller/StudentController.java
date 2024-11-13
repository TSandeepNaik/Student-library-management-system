package com.demo.example.student_library_management.controller;

import com.demo.example.student_library_management.converters.StudentConverter;
import com.demo.example.student_library_management.dto.StudentRequestDto;
import com.demo.example.student_library_management.model.Student;
import com.demo.example.student_library_management.service.StudentServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAll")
    public  List<Student> getAllStudent(){
       List<Student> students = studentServiece.getAllStudent();
       return students;
    }

    @GetMapping("/findById/{studentId}")   //here thid studentID is a path variable
    public Student getStudentById(@PathVariable("studentId") int studentId){
       Student student = studentServiece.studentById(studentId);
       return student;

    }

    //when ever we delete student card ass with the student also get deleted
    @DeleteMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable("studentId") int studentId){
       String response = studentServiece.deleteStudentById(studentId);
       return response;
    }

    //to find the count
    @GetMapping("/countCount")
    public String totalStudents(){
       String numOfStudents = studentServiece.countStudents();
       return numOfStudents;
    }

    //to update the student id - we use the existing the studentId and the passing data will override the same student
     // we use put mapping to update
    @PutMapping("/update/{studentId}")
    public String updateStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable("studentId") int studentId){
      String response = studentServiece.updateStudent(studentRequestDto, studentId);
      return  response;
    }
}
