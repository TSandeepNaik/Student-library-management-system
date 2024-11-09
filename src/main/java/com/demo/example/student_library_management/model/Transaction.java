package com.demo.example.student_library_management.model;

import com.demo.example.student_library_management.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
@Builder  //helps to build the objects and set it values we are going to use this builder in converter class
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transaction_status", nullable = false)  //for status will create the enum
    @Enumerated(value = EnumType.STRING)  // enum is converted into string        
    private TransactionStatus transactionStatus;  //changed the data type

    @Column(nullable = false)
    private double fine;

    @Column(name = "transaction_date", nullable = false)
    @CreationTimestamp    // it will take the creation time automatically
    private Date transactionDate;

    @Column(name = "is_issue_operation", nullable = false)
    private boolean isIssueOperation;  //if is issued it will give true else it will give false


    @ManyToOne  // many transaction could be done for one card like issued , returned, etc
    @JoinColumn  // taking card as  a foreign key in the transaction table
    private  Card card;

  @ManyToOne  // many transaction for one book
  @JoinColumn // taking book id as a foreign  key to transaction table
  private  Book book;
}
