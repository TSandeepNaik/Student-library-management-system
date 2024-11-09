package com.demo.example.student_library_management.converters;

import com.demo.example.student_library_management.dto.AuthorRequestDto;
import com.demo.example.student_library_management.model.Author;

public class AuthorConverter {

    //here we are taking author request dto as input and converting it into Author model class and later it will save in database
    public Author covertAuthorDtoIntoAuthor(AuthorRequestDto authorRequestDto){

         Author author = Author.builder().name(authorRequestDto.getName()).email(authorRequestDto.getEmail())
                .country(authorRequestDto.getCountry()).ratings(authorRequestDto.getRatings()).build();

         return author;
    }
}
