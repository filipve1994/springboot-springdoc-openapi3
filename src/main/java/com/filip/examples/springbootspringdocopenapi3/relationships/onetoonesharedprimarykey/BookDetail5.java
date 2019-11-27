package com.filip.examples.springbootspringdocopenapi3.relationships.onetoonesharedprimarykey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "book_detail5")
public class BookDetail5 implements Serializable {

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Id
    @OneToOne
    @JoinColumn(name = "book5_id")
    private Book5 book;

    public BookDetail5(){

    }

    public BookDetail5(Integer numberOfPages){
        this.numberOfPages = numberOfPages;
    }

}
