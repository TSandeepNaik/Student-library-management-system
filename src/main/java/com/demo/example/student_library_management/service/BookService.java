package com.demo.example.student_library_management.service;

import com.demo.example.student_library_management.converters.BookConverter;
import com.demo.example.student_library_management.converters.CardConverter;
import com.demo.example.student_library_management.dto.BookRequestDto;
import com.demo.example.student_library_management.model.Author;
import com.demo.example.student_library_management.model.Book;
import com.demo.example.student_library_management.model.Card;
import com.demo.example.student_library_management.repository.AuthorRepository;
import com.demo.example.student_library_management.repository.BookRepository;
import com.demo.example.student_library_management.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //first we create a object for the book repository to use its inbuilt functions
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public  String addBook(BookRequestDto bookRequestDto){
      Book book =  BookConverter.convertBookRequestDtoIntoBook(bookRequestDto);
      //along with the book entities we have cardId and AuthorId also we need to add those values also
        //for that we create the object for the card repository and author repository and
        //we add entire class not only the id
      Card card = cardRepository.findById(bookRequestDto.getCardId()).get();
      book.setCard(card);
 //similarly  for Author
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        book.setAuthor(author);

        bookRepository.save(book);

        return "Book added successfully";
    }
}
