package com.filip.examples.springbootspringdocopenapi3.repositories;

import com.filip.examples.springbootspringdocopenapi3.models.Category;
import com.filip.examples.springbootspringdocopenapi3.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
