package com.demo.example.student_library_management.service;

import com.demo.example.student_library_management.Enums.Cardstatus;
import com.demo.example.student_library_management.converters.StudentConverter;
import com.demo.example.student_library_management.dto.StudentRequestDto;
import com.demo.example.student_library_management.model.Card;
import com.demo.example.student_library_management.model.Student;
import com.demo.example.student_library_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
