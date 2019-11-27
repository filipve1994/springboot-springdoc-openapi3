package com.filip.examples.springbootspringdocopenapi3.relationships.onetoonesharedprimarykey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Book5Repository extends JpaRepository<Book5, Integer> {
}
