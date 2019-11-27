package com.filip.examples.springbootspringdocopenapi3.relationships.manytomanyextracolumns2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

//https://hellokoding.com/jpa-many-to-many-extra-columns-relationship-mapping-example-with-spring-boot-maven-and-mysql/
@Component
public class InitBooks6CommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitBooks6CommandLineRunner.class);

    @Autowired
    private Book6Repository book6Repository;

    @Autowired
    private Publisher6Repository publisher6Repository;

    @Override
    public void run(String... args) throws Exception {

        // Create a couple of Book, Publisher and BookPublisher
        Publisher6 publisherA = new Publisher6("Publisher A");
        Publisher6 publisherB = new Publisher6("Publisher B");
        publisher6Repository.saveAll(Arrays.asList(publisherA, publisherB));

        book6Repository.save(new Book6("Book 1", new Book6Publisher6(publisherA, new Date()), new Book6Publisher6(publisherB, new Date())));
        book6Repository.save(new Book6("Book 2", new Book6Publisher6(publisherA, new Date())));


    }
}