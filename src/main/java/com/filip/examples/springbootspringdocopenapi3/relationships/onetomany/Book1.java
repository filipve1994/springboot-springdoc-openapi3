package com.filip.examples.springbootspringdocopenapi3.relationships.onetomany;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/
@Data
@ToString
@Entity
public class Book1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "book_category1_id")
    private BookCategory1 bookCategory1;

    public Book1() {

    }

    public Book1(String name) {
        this.name = name;
    }
}
