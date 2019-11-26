package com.filip.examples.springbootspringdocopenapi3.relationships.manytomanyextracolumns;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Book3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "book3_publisher3",
//            joinColumns = @JoinColumn(name = "book3_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "publisher3_id", referencedColumnName = "id")
//    )
//    private Set<Publisher3> publishers;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book3Publisher3> bookPublishers;

    public Book3() {

    }

    public Book3(String name) {

        this.name = name;
        bookPublishers = new HashSet<>();
    }

}