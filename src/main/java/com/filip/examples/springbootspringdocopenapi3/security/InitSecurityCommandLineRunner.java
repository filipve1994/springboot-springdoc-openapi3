package com.filip.examples.springbootspringdocopenapi3.security;

import com.filip.examples.springbootspringdocopenapi3.security.models.Role;
import com.filip.examples.springbootspringdocopenapi3.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitSecurityCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Role role1 = new Role();
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        roleRepository.save(role1);
        roleRepository.save(role2);

    }
}