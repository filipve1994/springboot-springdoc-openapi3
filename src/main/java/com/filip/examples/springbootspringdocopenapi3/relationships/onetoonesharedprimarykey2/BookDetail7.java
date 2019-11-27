package com.filip.examples.springbootspringdocopenapi3.relationships.onetoonesharedprimarykey2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class BookDetail7 implements Serializable {

    @Id
    private Integer id;

    @OneToOne
    @JoinColumn
    @MapsId
    private Book7 book;

    private int numberOfPages;

    public BookDetail7() {

    }

    public BookDetail7(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

}
