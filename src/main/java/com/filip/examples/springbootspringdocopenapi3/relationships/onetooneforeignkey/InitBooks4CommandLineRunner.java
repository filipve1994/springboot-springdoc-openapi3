package com.filip.examples.springbootspringdocopenapi3.relationships.onetooneforeignkey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//https://hellokoding.com/jpa-one-to-one-foreign-key-relationship-mapping-example-with-spring-boot-hsql/
@Component
public class InitBooks4CommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitBooks4CommandLineRunner.class);

    @Autowired
    private Book4Repository book4Repository;

    @Override
    public void run(String... args) throws Exception {
// save a couple of books
        List<Book4> books = new ArrayList<>();
        books.add(new Book4("Book A", new BookDetail4(49)));
        books.add(new Book4("Book B", new BookDetail4(59)));
        books.add(new Book4("Book C", new BookDetail4(69)));
        book4Repository.saveAll(books);

        // fetch all books
        for (Book4 book : book4Repository.findAll()) {
            logger.info(book.toString());
        }

    }
}