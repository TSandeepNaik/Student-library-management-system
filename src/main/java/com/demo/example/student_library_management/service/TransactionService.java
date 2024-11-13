package com.demo.example.student_library_management.service;

import com.demo.example.student_library_management.converters.TransactionConverter;
import com.demo.example.student_library_management.dto.TransactionRequestDto;
import com.demo.example.student_library_management.model.Book;
import com.demo.example.student_library_management.model.Card;
import com.demo.example.student_library_management.model.Transaction;
import com.demo.example.student_library_management.repository.BookRepository;
import com.demo.example.student_library_management.repository.CardRepository;
import com.demo.example.student_library_management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    //create object for transaction repository
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    public  String addTransaction(TransactionRequestDto transactionRequestDto){
       Transaction transaction =TransactionConverter.convertTransactionRequestDtoIntoTransaction(transactionRequestDto);


       // apart from normal field we have bookId and cardId also so we need to create object and save those also in transaction
           Book book = bookRepository.findById(transactionRequestDto.getBookId()).get();
            transaction.setBook(book);

            //same here also we are searching the class using it's id using repository
           Card card = cardRepository.findById(transactionRequestDto.getCardId()).get();
       transaction.setCard(card);

        transactionRepository.save(transaction);
       return "transaction saved successfully";
    }

}
