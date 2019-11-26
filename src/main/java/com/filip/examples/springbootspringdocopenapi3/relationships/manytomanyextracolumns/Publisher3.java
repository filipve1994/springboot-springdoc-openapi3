package com.filip.examples.springbootspringdocopenapi3.relationships.manytomanyextracolumns;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class Publisher3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

//    @ManyToMany(mappedBy = "publishers", fetch = FetchType.EAGER)
//    private Set<Book3> books;

    @OneToMany(mappedBy = "publisher")
    private Set<Book3Publisher3> bookPublishers;

    public Publisher3() {

    }

    public Publisher3(String name) {
        this.name = name;
    }

}