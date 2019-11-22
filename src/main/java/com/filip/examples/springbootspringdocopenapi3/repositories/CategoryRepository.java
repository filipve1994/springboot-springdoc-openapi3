package com.filip.examples.springbootspringdocopenapi3.repositories;

import com.filip.examples.springbootspringdocopenapi3.models.Category;
import com.filip.examples.springbootspringdocopenapi3.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
