package com.filip.examples.springbootspringdocopenapi3.security.services;

import com.filip.examples.springbootspringdocopenapi3.security.models.ApplicationUser;

public interface ApplicationUserService {

    void save(ApplicationUser applicationUser);

    ApplicationUser findByUsername(String username);


}
