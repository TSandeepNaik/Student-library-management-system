package com.demo.example.student_library_management.controller;


import com.demo.example.student_library_management.dto.BookRequestDto;
import com.demo.example.student_library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/api")
public class BookController {

    //create object for book service
    @Autowired
    private BookService bookService;

    @PostMapping("/adding/Book")         // here we are taking the object so we are using requestBody annotation
    public String addBook(@RequestBody BookRequestDto bookRequestDto){
        String response = bookService.addBook(bookRequestDto);
        return response;
    }
}
