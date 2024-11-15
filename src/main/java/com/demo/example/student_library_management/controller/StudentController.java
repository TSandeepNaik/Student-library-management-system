package com.demo.example.student_library_management.controller;

import com.demo.example.student_library_management.dto.StudentRequestDto;
import com.demo.example.student_library_management.model.Student;
import com.demo.example.student_library_management.service.StudentServiece;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/api")
public class StudentController {

    @Autowired  //we are creating the object for the student service class to use the savestudent fun and write the api
    private StudentServiece studentServiece;

    //Response entity - it is inbuilt class to send the standard response from api's
    //to implement it need to change the data type to ResponseEntity< return data-type> and return type to ResponseEntity.ok(return-ans);
    //Loggers - displays the msg in console which helps in tracking of the application flow
    /*
    different levels of loggers
    info - displays the normal information
    error - displays error or exception messages
    warn - displays the warning messages
    debug - while running the application in debug mode
    =loggers always used with slf4j

    */
    //writing logger in control class and also in service class
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/save")  // to save the student data we use post mapping
    public ResponseEntity<?> saveStudent(@RequestBody StudentRequestDto studentRequestDto){  // for safer side we use ? as a datatype in all methods
        //we write all our code in try block
        logger.info("saveStudent api started");
        try {
            String response = studentServiece.addStudent(studentRequestDto);
            logger.info("saveStudent api ended");
            return ResponseEntity.ok(response);   //ok means success
        } catch (Exception e) {
            logger.error("saveStudent api caught with an exception : "+e.getMessage());
                return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?>  getAllStudent(){   //here instead of listOfStudent we are using ? because in try block it is returning list of student and
        //in catch block it is returning a string msg but we cant return two types from one method so we use ? to generalise the data type now we can return
        // both string and a listOfStudent
        try {
            List<Student> students = studentServiece.getAllStudent();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/findById/{studentId}")   //here thid studentID is a path variable
    public ResponseEntity<?>  getStudentById(@PathVariable("studentId") int studentId){
        logger.info("find student by ID API is started");
        try {
            Student student = studentServiece.studentById(studentId);
            logger.info("find student by ID API is ended");
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            logger.error("find student by id  api caught with an exception : "+e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    //when ever we delete student card ass with the student also get deleted
    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") int studentId){
        try {
            String response = studentServiece.deleteStudentById(studentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //to find the count
    @GetMapping("/countCount")
    public ResponseEntity<?>  totalStudents(){
        try {
            String numOfStudents = studentServiece.countStudents();
            return ResponseEntity.ok(numOfStudents);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //to update the student id - we use the existing the studentId and the passing data will override the same student
     // we use put mapping to update
    @PutMapping("/update/{studentId}")
    public ResponseEntity<?>  updateStudent(@RequestBody StudentRequestDto studentRequestDto, @PathVariable("studentId") int studentId){
        try {
            String response = studentServiece.updateStudent(studentRequestDto, studentId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //api for pagination
    //here by default il will be sorted based on id
    //to sort based on whatever we want we give one more input like sortingInput
    @GetMapping("/getPages")  //here we created sortInput variable extra based on which we can sort the pages
    public  List<Student> getStudentWithPages(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("sortInput") String sortInput){
      List<Student> studentList = studentServiece.getAllStudentByPages(pageNum, pageSize,sortInput);
      return studentList;
    }

  //find student by email
    @GetMapping("/findByMail")
    public Student findStudent(@RequestParam("email") String email){
        Student student = studentServiece.findStudetnByEmail(email);
        return student;
    }
    //it is not working
 //own query to search the student by email
    @GetMapping("/emailQuery")
    public  Student searchStudentQuery(@RequestParam("email") String email){
        Student student = studentServiece.searchStudentQuery(email);
        return student;
    }

}
