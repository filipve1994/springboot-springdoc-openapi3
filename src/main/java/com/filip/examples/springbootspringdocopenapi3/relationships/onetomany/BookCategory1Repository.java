package com.filip.examples.springbootspringdocopenapi3.relationships.onetomany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategory1Repository extends JpaRepository<BookCategory1, Integer> {

    BookCategory1 findByName(String name);
}
