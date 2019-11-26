package com.filip.examples.springbootspringdocopenapi3.relationships.onetomany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/
@Data
@EqualsAndHashCode(exclude = "books")
@ToString(exclude = "books")
@Entity
public class BookCategory1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "bookCategory1", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Book1> books;

    public BookCategory1() {

        this.books = new HashSet<>();

    }

    public BookCategory1(String name, Book1... books) {
        this.name = name;
        this.books = Stream.of(books).collect(Collectors.toSet());
        this.books.forEach(x -> x.setBookCategory1(this));
    }
}
