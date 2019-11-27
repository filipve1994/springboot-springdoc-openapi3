package com.filip.examples.springbootspringdocopenapi3.security.services;

import com.filip.examples.springbootspringdocopenapi3.security.models.ApplicationUser;
import com.filip.examples.springbootspringdocopenapi3.security.models.UserPrincipal;
import com.filip.examples.springbootspringdocopenapi3.security.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        //ApplicationUser user = applicationUserRepository.findByUsername(username);
        ApplicationUser user = applicationUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        ApplicationUser user = applicationUserRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}
