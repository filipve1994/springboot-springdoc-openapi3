package com.filip.examples.springbootspringdocopenapi3.relationships.onetooneforeignkey2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//https://hellokoding.com/jpa-one-to-one-foreign-key-relationship-mapping-example-with-spring-boot-hsql/
@Component
public class InitLibraryCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitLibraryCommandLineRunner.class);

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create a couple of Library and Address
        libraryRepository.save(new Library("Library 1", new Address("Street 1", "Zip 1")));
        libraryRepository.save(new Library("Library 2", new Address("Street 2", "Zip 2")));

    }
}