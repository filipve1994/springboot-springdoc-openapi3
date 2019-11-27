package com.filip.examples.springbootspringdocopenapi3.relationships.onetooneforeignkey2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// https://hellokoding.com/jpa-one-to-one-foreignkey-relationship-example-with-spring-boot-maven-and-mysql/
@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;

    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Library library;

    public Address(){

    }

    public Address(String street, String zipCode) {
        this.street = street;
        this.zipCode = zipCode;
    }
}
