package com.demo.example.student_library_management.converters;

import com.demo.example.student_library_management.dto.TransactionRequestDto;
import com.demo.example.student_library_management.model.Transaction;

public class TransactionConverter {
// here we are taking the input from requestDto and converting it into model class using method which returns class
    public static Transaction convertTransactionRequestDtoIntoTransaction(TransactionRequestDto transactionRequestDto){
       Transaction transaction = Transaction.builder().transactionStatus(transactionRequestDto.getTransactionStatus())
                .fine(transactionRequestDto.getFine()).isIssueOperation(transactionRequestDto.isIssueOperation()).build();

       return transaction;
    }
}
