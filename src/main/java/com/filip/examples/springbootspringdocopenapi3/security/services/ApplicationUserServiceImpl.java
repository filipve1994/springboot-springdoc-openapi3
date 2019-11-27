package com.filip.examples.springbootspringdocopenapi3.security.services;

import com.filip.examples.springbootspringdocopenapi3.repositories.UserRepository;
import com.filip.examples.springbootspringdocopenapi3.security.models.ApplicationUser;
import com.filip.examples.springbootspringdocopenapi3.security.repositories.ApplicationUserRepository;
import com.filip.examples.springbootspringdocopenapi3.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void save(ApplicationUser applicationUser) {
        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        applicationUser.setRoles(new HashSet<>(roleRepository.findAll()));
        applicationUserRepository.save(applicationUser);
    }

    @Override
    public ApplicationUser findByUsername(String username) {
        return applicationUserRepository.findByUsername(username);
    }
}
