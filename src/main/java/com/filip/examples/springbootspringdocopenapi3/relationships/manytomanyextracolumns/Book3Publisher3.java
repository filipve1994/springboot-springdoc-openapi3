package com.filip.examples.springbootspringdocopenapi3.relationships.manytomanyextracolumns;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book3_publisher3")
@Getter
@Setter
public class Book3Publisher3 implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book3 book;

    @Id
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher3 publisher;

    @Column(name = "published_date")
    private Date publishedDate;

}
