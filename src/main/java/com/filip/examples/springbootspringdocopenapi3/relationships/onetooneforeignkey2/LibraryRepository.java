package com.filip.examples.springbootspringdocopenapi3.relationships.onetooneforeignkey2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
}
