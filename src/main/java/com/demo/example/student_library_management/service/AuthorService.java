package com.demo.example.student_library_management.service;

import com.demo.example.student_library_management.converters.AuthorConverter;
import com.demo.example.student_library_management.dto.AuthorRequestDto;
import com.demo.example.student_library_management.model.Author;
import com.demo.example.student_library_management.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    //let's create object for the author repository
    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(AuthorRequestDto authorRequestDto){
       //we call the author converter which converts the author dto to author model class then we save it using repository
         Author author = AuthorConverter.covertAuthorDtoIntoAuthor(authorRequestDto);

         authorRepository.save(author);

         return "author saved successfully";
    }
}
