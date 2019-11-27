package com.filip.examples.springbootspringdocopenapi3.relationships.onetoonesharedprimarykey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//https://hellokoding.com/jpa-one-to-one-foreign-key-relationship-mapping-example-with-spring-boot-hsql/
@Component
public class InitBooks5CommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitBooks5CommandLineRunner.class);

    @Autowired
    private Book5Repository book4Repository;

    @Override
    public void run(String... args) throws Exception {
// save a couple of books
        List<Book5> books = new ArrayList<>();
        books.add(new Book5("Book A", new BookDetail5(49)));
        books.add(new Book5("Book B", new BookDetail5(59)));
        books.add(new Book5("Book C", new BookDetail5(69)));
        book4Repository.saveAll(books);

        // fetch all books
        for (Book5 book : book4Repository.findAll()) {
            logger.info(book.toString());
        }

    }
}