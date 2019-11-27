package com.filip.examples.springbootspringdocopenapi3.security.repositories;

import com.filip.examples.springbootspringdocopenapi3.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
