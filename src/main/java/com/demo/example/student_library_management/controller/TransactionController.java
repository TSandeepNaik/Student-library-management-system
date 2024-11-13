package com.demo.example.student_library_management.controller;

import com.demo.example.student_library_management.dto.TransactionRequestDto;
import com.demo.example.student_library_management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction/api")
public class TransactionController {

    //create object for transaction service class
    @Autowired
    private TransactionService transactionService;

    @PostMapping("saving/transaction")
    public String  addTransaciton( @RequestBody TransactionRequestDto transactionRequestDto){
        String response = transactionService.addTransaction(transactionRequestDto);

        return response;

    }
}
