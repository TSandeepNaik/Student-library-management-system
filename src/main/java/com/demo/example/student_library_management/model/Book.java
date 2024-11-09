package com.demo.example.student_library_management.model;

import com.demo.example.student_library_management.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "book")
@Builder  //helps to build the objects and set it values we are going to use this builder in converter class
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)  //converting the enum to string
    private Genre genre;   //it should be fixed like comedy drama action fiction etc
    //so we create enum for this and we change the data type to the enum name

    @Column(nullable = false)
   private  int pages;

    @Column(name = "publisher_name")
   private  String PublisherName;

    @Column(name = "issued_to_student", nullable = false)
   private boolean issuedToStudent;

    @ManyToOne   // from book to author is many-to-one relation
    @JoinColumn  // to join the author id to book as a foreign key
    private Author author;

    @ManyToOne  // many books can brought using one card
    @JoinColumn  //taking card id as a foreign key in the book class/table
    private Card card;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)  //one book can have many transaction
    private List<Transaction> listOfTransavtions = new ArrayList<>();


}
