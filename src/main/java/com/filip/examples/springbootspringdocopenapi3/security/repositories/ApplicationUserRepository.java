package com.filip.examples.springbootspringdocopenapi3.security.repositories;

import com.filip.examples.springbootspringdocopenapi3.models.User;
import com.filip.examples.springbootspringdocopenapi3.security.models.ApplicationUser;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByEmail(String email);

    ApplicationUser findByUsernameOrEmail(String username, String email);

    List<ApplicationUser> findByIdIn(List<Long> userIds);

    ApplicationUser findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
