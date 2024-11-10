package com.demo.example.student_library_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data       //for getter and setter
@Entity   // it will tell this is a model class
@Table(name = "student")
@Builder  //helps to build the objects and set it values we are going to use this builder in converter class
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id    //it will create the id column as a primary key
    @Column     //because it is a primary key it cant be duplicated so no need to add nullable=false
    //in a system we will add every field and id will generate automatically so to achieve it we use below annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //it will create id from 1 and it will increase +1 when next transaction happened
  private  int id;

    @Column(nullable = false)
  private String name;

    @Column(nullable = false, unique = true)
  private  String email;

    @Column(nullable = true)
  private int age;

    @Column(nullable = false)
  private String address;

    @Column(nullable = false, unique = true)
  private String mobile;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)  //mapped by the same name given in the card class while
    // creating the student object
    //cascade used if we do anything in card regarding student class it will automatcally updated in student class also
    private Card card;
}
