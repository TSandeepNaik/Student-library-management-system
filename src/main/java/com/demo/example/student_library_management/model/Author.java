package com.demo.example.student_library_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "author")
@Builder  //helps to build the objects and set it values we are going to use this builder in converter class
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private  String country;

    @Column(nullable = false)
    private  double ratings;

    @JsonManagedReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)  //here we are mapping the author with the book
    //and using the cascade if we update anything in this author class it will automatically update in the book also where
    //we use this author class as foreign key
    private List<Book> bookByAuthor = new ArrayList<>();
//here we are initializing as a list of books because it one to many mapping one author writing many books
    // list is initializing as  arraylist.
}
