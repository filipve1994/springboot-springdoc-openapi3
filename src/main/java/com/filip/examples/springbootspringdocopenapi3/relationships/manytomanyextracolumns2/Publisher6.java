package com.filip.examples.springbootspringdocopenapi3.relationships.manytomanyextracolumns2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Publisher6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private Set<Book6Publisher6> bookPublishers = new HashSet<>();

    public Publisher6() {

    }

    public Publisher6(String name) {
        this.name = name;
    }

}