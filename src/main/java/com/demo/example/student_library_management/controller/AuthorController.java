package com.demo.example.student_library_management.controller;


import com.demo.example.student_library_management.dto.AuthorRequestDto;
import com.demo.example.student_library_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Author/api")
public class AuthorController {

    //here we autowire the Author service class
    @Autowired
    private AuthorService authorService;

    @PostMapping("/saveAuthor")
    public String saveAuthor( @RequestBody AuthorRequestDto authorRequestDto){
        String response = authorService.addAuthor(authorRequestDto);
        return response;
    }
}
