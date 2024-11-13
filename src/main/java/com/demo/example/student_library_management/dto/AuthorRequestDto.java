package com.demo.example.student_library_management.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AuthorRequestDto {

        private String name;
        private String email;
        private  String country;
        private  double ratings;
}
