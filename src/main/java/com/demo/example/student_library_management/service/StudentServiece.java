package com.demo.example.student_library_management.service;

import com.demo.example.student_library_management.Enums.Cardstatus;
import com.demo.example.student_library_management.converters.StudentConverter;
import com.demo.example.student_library_management.dto.StudentRequestDto;
import com.demo.example.student_library_management.model.Card;
import com.demo.example.student_library_management.model.Student;
import com.demo.example.student_library_management.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiece {

    @Autowired   //here we are creating object for the repository class so that we can use tha inbuilt functions of repository
    private StudentRepository studentRepository;

    //creating object for the loggers
    Logger logger = LoggerFactory.getLogger(StudentServiece.class);

//here we are  not taking student class directly instead we are taking the dto classes where we written the entities of the model class
    public String addStudent(StudentRequestDto studentRequestDto){
        logger.info("addStudent method started");
        Student student = StudentConverter.convertStudentDtoIntoStudent(studentRequestDto);
          // here we are calling the student converter to save the student data because we are taking input from the dto class
        // which is not in the object form so converter classes will convert then into model class and will call the converter and save the student


        //when ever student get created a card also get create cas as soon a student come to library along with saving his
        //saving his details will issue a card also  so we create a card here only
        Card card = new Card();
        card.setCardstatus(Cardstatus.ACTIVE);  // when we issue the card it will be in active state so we use enum value

        //we need to set card in the student and student in the card    1-1 mapping
        card.setStudent(student);
        student.setCard(card);
        logger.info("student along with card details saving in database");
        studentRepository.save(student);  // here we are saving student but not card but we used cascade all in the student class so that
        // card will save automatically
    logger.info("addStudent method ended");
        return "student and card are created";
    }

    //let us write the function for get all student and get student by id
    public  List<Student> getAllStudent(){
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()){
            throw new RuntimeException("Students are not present");
        }
        return students;
    }

    /*
    pagination - getting/fetching  the record in the form of pages like in amazon page to page search are there
    we always pass the page number and page size as input and page number always starts from 0 only
    example - if database have 18 records and page size is 5 then
    page 0 = 1-5
    page 1 = 6-10
    page 2 = 11-15
    page 3 = 16-18
     */

    //pagination logics
    public List<Student> getAllStudentByPages(int pageNum, int pageSize, String sortInput){
        //to sort we use sorting Sort.by()  - here sort is of domain type - and inside the bracket u can add any proeprties based on which u want to sort and
        //you can also add ascending or descending fun at the end
        //now it will sort on whatever we give as input
       Page<Student> studentPage = studentRepository.findAll(PageRequest.of(pageNum,pageSize, Sort.by(sortInput).ascending())); //here this findAll is of type pageble pageble
        //we can not send studentPage directly so we convert it into list and then we send
        List<Student> studentList = new ArrayList<>();
        for(Student stu1 : studentPage){
            studentList.add(stu1);
        }
        return studentList;
    }

    //get by id
    public  Student studentById(int studentId){   //here whatever we take the variable name is just for name sake it will take the value of id which we pass in controller
     logger.info("studentById method is started");
       Student student = studentRepository.findById(studentId).get();
       if(student==null){
           logger.error("student not found");
           throw new RuntimeException("student is not present with the id: "+studentId);
       }
       logger.info("studentById method is ended");
       return student;
    }

    //delete student by id
    public String deleteStudentById(int studentId){
        studentRepository.deleteById(studentId);
        return "student deleted with the id : "+studentId;
    }

    //to get the total count of students
    public String countStudents(){
       long totalCount = studentRepository.count();
       return "the total count of students are :"+totalCount;
    }

    //to update the student
    public String updateStudent(StudentRequestDto studentRequestDto, int studentId){
        Student student = studentById(studentId);
    if(student==null){
        throw new RuntimeException("no student is present with the student id: "+studentId);
    }
        if(student != null){  //if student is not null will override the same student
          //First we convert this new  request dto
            //here we should not create the new object we should use the old object to override the student details
           //we don't use the converter again for the same class so will manually update the value
            student.setName(studentRequestDto.getName());
            student.setAge(studentRequestDto.getAge());
            student.setEmail(studentRequestDto.getEmail());
            student.setMobile(studentRequestDto.getMobile());
            student.setAddress(studentRequestDto.getAddress());
            studentRepository.save(student);
           return "student get updated with the id :"+studentId;
        }else{
            return "student not found can not update";
        }
    }
//find student by emil
     public Student findStudetnByEmail(String email){
        Student student = studentRepository.findByEmail(email);
        return student;
     }

     //it is not working
    // find student by email own query
    public  Student searchStudentQuery(String email){
        Student student = studentRepository.searchByEmailQuery(email);
        return student;
    }


}
