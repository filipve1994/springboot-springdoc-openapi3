package com.filip.examples.springbootspringdocopenapi3.relationships.onetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/
@Component
public class InitBooks1CommandLineRunner implements CommandLineRunner {

    @Autowired
    private BookCategory1Repository bookCategory1Repository;

    @Override
    public void run(String... args) throws Exception {

        BookCategory1 bookCategory1 = new BookCategory1();
        bookCategory1.setName("Category 1");

        bookCategory1Repository.save(bookCategory1);

        Set<Book1> books = new HashSet<>();

        Book1 book1 = new Book1();
        book1.setName("Hello Koding 1");
        books.add(book1);
        book1.setBookCategory1(bookCategory1);

        Book1 book2 = new Book1();
        book2.setName("Hello Koding 2");
        book2.setBookCategory1(bookCategory1);

        books.add(book2);

        bookCategory1.setBooks(books);

        bookCategory1Repository.save(bookCategory1);

        //-----------------------------------------------------

        // Create a couple of Book and BookCategory
        bookCategory1Repository.save(new BookCategory1("Category 2", new Book1("Hello Koding 3"), new Book1("Hello Koding 4")));

        List<BookCategory1> all = bookCategory1Repository.findAll();
        System.out.println(all.size());

        for (BookCategory1 bb : all) {
            int size = bb.getBooks().size();

            System.out.println("size is : " + size);

            System.out.println(bb.toString());
        }


    }
}