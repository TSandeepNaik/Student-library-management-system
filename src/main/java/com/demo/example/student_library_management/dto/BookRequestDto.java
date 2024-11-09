package com.demo.example.student_library_management.dto;

import com.demo.example.student_library_management.Enums.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class BookRequestDto {
    //here we need to add foreign key like author id and card id

    private String name;
    private Genre genre;   //it should be fixed like comedy drama action fiction etc
    //so we create enum for this and we change the data type to the enum name
    private  int pages;
    private  String PublisherName;
    private boolean issuedToStudent;
    private int authorId;
    private int cardId;

}
