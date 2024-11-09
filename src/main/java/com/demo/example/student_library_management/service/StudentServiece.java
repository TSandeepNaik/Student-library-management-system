package com.demo.example.student_library_management.service;

import com.demo.example.student_library_management.Enums.Cardstatus;
import com.demo.example.student_library_management.controller.StudentController;
import com.demo.example.student_library_management.converters.StudentConverter;
import com.demo.example.student_library_management.dto.StudentRequestDto;
import com.demo.example.student_library_management.model.Card;
import com.demo.example.student_library_management.model.Student;
import com.demo.example.student_library_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiece {

    @Autowired
    private StudentRepository studentRepository;


    public String addStudent(StudentRequestDto studentRequestDto){
        Student student = new Student();
        student =    StudentConverter.convertStudentDtoIntoStudent(studentRequestDto);

        //when ever student get created a card also get create cas as soon a student come to library along with saving his
        //saving his details will issue a card also  so we create a card here only

        Card card = new Card();
        card.setCardstatus(Cardstatus.ACTIVE);  // when we issue the card it will be in active state so we use enum value

        //we need to set card in the student and student in the card    1-1 mapping
        card.setStudent(student);
        student.setCard(card);

        studentRepository.save(student);

        return "student and card are created";
    }
}
