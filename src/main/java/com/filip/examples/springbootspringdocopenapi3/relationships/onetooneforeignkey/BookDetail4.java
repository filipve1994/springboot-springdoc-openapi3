package com.filip.examples.springbootspringdocopenapi3.relationships.onetooneforeignkey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "book_detail4")
public class BookDetail4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @OneToOne(mappedBy = "bookDetail")
    private Book4 book;

    public BookDetail4(){

    }

    public BookDetail4(Integer numberOfPages){
        this.numberOfPages = numberOfPages;
    }

}
