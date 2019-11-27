package com.filip.examples.springbootspringdocopenapi3.relationships.onetoonesharedprimarykey2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//https://hellokoding.com/jpa-one-to-one-shared-primary-key-relationship-mapping-example-with-spring-boot-maven-and-mysql/
@Component
public class InitBooks7CommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitBooks7CommandLineRunner.class);

    @Autowired
    private Book7Repository book7Repository;

    @Override
    public void run(String... args) throws Exception {
        // Create a couple of Book and BookDetail
        book7Repository.save(new Book7("Hello Koding 1", new BookDetail7(101)));
        book7Repository.save(new Book7("Hello Koding 2", new BookDetail7(102)));

    }
}