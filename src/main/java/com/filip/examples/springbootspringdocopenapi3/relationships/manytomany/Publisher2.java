package com.filip.examples.springbootspringdocopenapi3.relationships.manytomany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = "books")
@Entity
public class Publisher2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "publishers", fetch = FetchType.EAGER)
    private Set<Book2> books;

    public Publisher2() {

    }

    public Publisher2(String name) {
        this.name = name;
    }

    public Publisher2(String name, Set<Book2> books) {
        this.name = name;
        this.books = books;
    }

    @Override
    public String toString() {
        String result = String.format(
                "Publisher [id=%d, name='%s']%n",
                id, name);
        if (books != null) {
            for (Book2 book : books) {
                result += String.format(
                        "Book[id=%d, name='%s']%n",
                        book.getId(), book.getName());
            }
        }

        return result;
    }
}