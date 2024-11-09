package com.demo.example.student_library_management.model;


import com.demo.example.student_library_management.Enums.Cardstatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "card")
@Builder  //helps to build the objects and set it values we are going to use this builder in converter class
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_status", nullable = false)
    @Enumerated(value = EnumType.STRING) //here we are using this annotation that our enums takes the values of type string
    private Cardstatus cardstatus;  //here our data type is a enum Cardstatus so it will take only those values present
                                   // in the enum

    @Column(name = "createdDate", nullable = false)
    @CreationTimestamp   //it will add the time when card is created
    private Date createdDate;

    @Column(name = "updatedDate", nullable = false)
    @UpdateTimestamp        // it will add the time when card is updated
    private Date updatedDate;

    @OneToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL) //one card will have many books borrowed
    private List<Book> listOfBookForCard = new ArrayList<>();
    //list of books for the one card

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL) // if we delete in card it will automatically get deleted in transaction also
    private List<Transaction> listOfTransactions = new ArrayList<>();
    // one card can have list of transactions, it is one many relation

}
