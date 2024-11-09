package com.demo.example.student_library_management.dto;

import com.demo.example.student_library_management.Enums.TransactionStatus;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class TransactionRequestDto {

    private TransactionStatus transactionStatus;  //changed the data type
    private double fine;
    private boolean isIssueOperation;  //if is issued it will give true else it will give false
    private int bookId;
    private int cardId;



}
