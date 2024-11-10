package com.demo.example.student_library_management.converters;

import com.demo.example.student_library_management.dto.StudentRequestDto;
import com.demo.example.student_library_management.model.Student;

public class StudentConverter {

    // it converts the student request dto into student model class later it will in database

//we are making it static so that we call it directly without creating an object
    //will call this function in service class while writing the business logics
    public static Student convertStudentDtoIntoStudent(StudentRequestDto studentRequestDto){
             //building the student class and setting the values here builder  helping in that process
       Student student =  Student.builder().name(studentRequestDto.getName()).age(studentRequestDto.getAge())
                .email(studentRequestDto.getEmail()).mobile(studentRequestDto.getMobile())
                .address(studentRequestDto.getAddress()).build();

       return student;
    }
}
