package com.filip.examples.springbootspringdocopenapi3.relationships.onetooneforeignkey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Book4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_detail4_id")
    private BookDetail4 bookDetail;

    public Book4(){

    }

    public Book4(String name){
        this.name = name;
    }

    public Book4(String name, BookDetail4 bookDetail){
        this.name = name;
        this.bookDetail = bookDetail;
    }

    @Override
    public String toString() {
        return String.format(
                "Book4[id=%d, name='%s', number of pages='%d']",
                id, name, bookDetail.getNumberOfPages());
    }
}
