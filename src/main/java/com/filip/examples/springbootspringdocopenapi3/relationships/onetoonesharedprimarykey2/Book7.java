package com.filip.examples.springbootspringdocopenapi3.relationships.onetoonesharedprimarykey2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

//https://hellokoding.com/jpa-one-to-one-shared-primary-key-relationship-mapping-example-with-spring-boot-maven-and-mysql/
@Entity
@Getter
@Setter
public class Book7 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "book")
    private BookDetail7 bookDetail;

    public Book7() {

    }

    public Book7(String name) {
        this.name = name;
    }

    public Book7(String name, BookDetail7 bookDetail) {
        this.name = name;
        this.bookDetail = bookDetail;
        this.bookDetail.setBook(this);
    }

    @Override
    public String toString() {
        return String.format(
                "Book7[id=%d, name='%s', number of pages='%d']",
                id, name, bookDetail.getNumberOfPages());
    }
}
