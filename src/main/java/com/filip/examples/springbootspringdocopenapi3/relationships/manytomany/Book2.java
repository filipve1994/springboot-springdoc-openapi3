package com.filip.examples.springbootspringdocopenapi3.relationships.manytomany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Book2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book2_publisher2",
            joinColumns = @JoinColumn(name = "book2_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "publisher2_id", referencedColumnName = "id")
    )
    private Set<Publisher2> publishers;

    public Book2() {

    }

    public Book2(String name) {
        this.name = name;
    }

    public Book2(String name, Set<Publisher2> publishers) {
        this.name = name;
        this.publishers = publishers;
    }


    @Override
    public String toString() {
        String result = String.format(
                "Book2 [id=%d, name='%s']%n",
                id, name);
        if (publishers != null) {
            for (Publisher2 publisher : publishers) {
                result += String.format(
                        "Publisher[id=%d, name='%s']%n",
                        publisher.getId(), publisher.getName());
            }
        }

        return result;
    }
}