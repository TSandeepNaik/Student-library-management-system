package com.demo.example.student_library_management.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data //for getter and setter cas we use private here
public class StudentRequestDto {
    //here we take all the entries which are present in the student class
    //we dont take id here because it is auto generated
    //here we are taking input parameters which are going as Requests
    //dto - data transfer object
//if we do some changes here we are not directly changing in the data base it will show change here only not in the main student class

    private String name;
    private  String email;
    private int age;
    private String address;
    private String mobile;
}
