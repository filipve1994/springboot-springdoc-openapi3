package com.filip.examples.springbootspringdocopenapi3.relationships.manytomany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

//https://hellokoding.com/jpa-many-to-many-relationship-mapping-example-with-spring-boot-hsql/
@Component
public class InitBooks2CommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitBooks2CommandLineRunner.class);

    @Autowired
    private Book2Repository book2Repository;

    @Autowired
    private Publisher2Repository publisher2Repository;

    @Override
    public void run(String... args) throws Exception {

        // save a couple of books
        Publisher2 publisherA = new Publisher2("Publisher A");
        Publisher2 publisherB = new Publisher2("Publisher B");
        Publisher2 publisherC = new Publisher2("Publisher C");
        Book2 bookA = new Book2("Book A", new HashSet<>(Arrays.asList(publisherA, publisherB)));
        Book2 bookB = new Book2("Book B", new HashSet<>(Arrays.asList(publisherA, publisherC)));
        book2Repository.saveAll(Arrays.asList(bookA, bookB));

        // fetch all books
        for(Book2 book : book2Repository.findAll()) {
            logger.info(book.toString());
        }



        //----------------------------------------------------

        // save a couple of publishers
        Book2 bookD = new Book2("Book D");
        Book2 bookE = new Book2("Book E");
        Book2 bookF = new Book2("Book F");
        Publisher2 publisherD = new Publisher2("Publisher D", new HashSet<Book2>(Arrays.asList(bookD, bookE)));
        Publisher2 publisherE = new Publisher2("Publisher E", new HashSet<Book2>(Arrays.asList(bookE, bookF)));
        publisher2Repository.saveAll(Arrays.asList(publisherD, publisherE));

        // fetch all publishers
        for(Publisher2 publisher : publisher2Repository.findAll()) {
            logger.info(publisher.toString());
        }


    }
}