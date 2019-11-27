package com.filip.examples.springbootspringdocopenapi3.relationships.manytomanyextracolumns2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
public class Book6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Book6Publisher6> bookPublishers;

    public Book6() {

    }

    public Book6(String name, Book6Publisher6... bookPublishers) {
        this.name = name;
        for(Book6Publisher6 bookPublisher : bookPublishers) bookPublisher.setBook(this);
        this.bookPublishers = Stream.of(bookPublishers).collect(Collectors.toSet());
    }
}