package com.demo.example.student_library_management.converters;

import com.demo.example.student_library_management.dto.BookRequestDto;
import com.demo.example.student_library_management.model.Book;

public class BookConverter {

    public static Book  convertBookRequestDtoIntoBook(BookRequestDto bookRequestDto){
        //here we cant take bookId and cardId cas they are saved as  objects
       Book book =  Book.builder().name(bookRequestDto.getName()).genre(bookRequestDto.getGenre())
                .issuedToStudent(bookRequestDto.isIssuedToStudent()).pages(bookRequestDto.getPages())
                .PublisherName(bookRequestDto.getPublisherName()).build();

        return book;

    }
}
