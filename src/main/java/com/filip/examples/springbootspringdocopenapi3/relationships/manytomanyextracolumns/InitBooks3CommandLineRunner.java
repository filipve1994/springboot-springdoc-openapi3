package com.filip.examples.springbootspringdocopenapi3.relationships.manytomanyextracolumns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

//https://hellokoding.com/jpa-many-to-many-relationship-mapping-example-with-spring-boot-hsql/
@Component
public class InitBooks3CommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitBooks3CommandLineRunner.class);

    @Autowired
    private Book3Repository book3Repository;

    @Autowired
    private Publisher3Repository publisher3Repository;

    @Override
    public void run(String... args) throws Exception {

        // create new
        Book3 bookA = new Book3("Book A");
        Publisher3 publisherA = new Publisher3("Publisher A");

        Book3Publisher3 bookPublisher = new Book3Publisher3();
        bookPublisher.setBook(bookA);
        bookPublisher.setPublisher(publisherA);
        bookPublisher.setPublishedDate(new Date());

        bookA.getBookPublishers().add(bookPublisher);

        publisher3Repository.save(publisherA);
        book3Repository.save(bookA);

        // test
        System.out.println("bookA publishers size: " + bookA.getBookPublishers().size());

        // update
        bookA.getBookPublishers().remove(bookPublisher);
//        book3Repository.save(bookA);
//
//        // test
//        System.out.println(bookA.getBookPublishers().size());


    }
}