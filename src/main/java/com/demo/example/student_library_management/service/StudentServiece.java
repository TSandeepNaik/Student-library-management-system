package com.demo.example.student_library_management.service;

import com.demo.example.student_library_management.Enums.Cardstatus;
import com.demo.example.student_library_management.converters.StudentConverter;
import com.demo.example.student_library_management.dto.StudentRequestDto;
import com.demo.example.student_library_management.model.Card;
import com.demo.example.student_library_management.model.Student;
import com.demo.example.student_library_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiece {

    @Autowired   //here we are creating object for the repository class so that we can use tha inbuilt functions of repository
    private StudentRepository studentRepository;

//here we are  not taking student class directly instead we are taking the dto classes where we written the entities of the model class
    public String addStudent(StudentRequestDto studentRequestDto){
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

        studentRepository.save(student);  // here we are saving student but not card but we used cascade all in the student class so that
        // card will save automatically

        return "student and card are created";
    }

    //let us write the function for get all student and get student by id
    public  List<Student> getAllStudent(){
        List<Student> students = studentRepository.findAll();
        return students;
    }

    //get by id
    public  Student studentById(int studentId){   //here whatever we take the variable name is just for name sake it will take the value of id which we pass in controller
       Student student = studentRepository.findById(studentId).get();
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
}
