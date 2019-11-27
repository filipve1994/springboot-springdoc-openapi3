package com.filip.examples.springbootspringdocopenapi3.relationships.manytomanyextracolumns2;

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
import java.util.Objects;

@Entity
@Table(name = "book6_publisher6")
@Getter
@Setter
public class Book6Publisher6 implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Book6 book;

    @Id
    @ManyToOne
    @JoinColumn
    private Publisher6 publisher;

    @Column(name = "published_date")
    private Date publishedDate;

    public Book6Publisher6(){

    }

    public Book6Publisher6(Publisher6 publisher, Date publishedDate) {
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book6Publisher6)) return false;
        Book6Publisher6 that = (Book6Publisher6) o;
        return Objects.equals(book.getName(), that.book.getName()) &&
                Objects.equals(publisher.getName(), that.publisher.getName()) &&
                Objects.equals(publishedDate, that.publishedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book.getName(), publisher.getName(), publishedDate);
    }
}
