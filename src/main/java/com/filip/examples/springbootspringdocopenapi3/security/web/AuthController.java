package com.filip.examples.springbootspringdocopenapi3.security.web;

import com.filip.examples.springbootspringdocopenapi3.errorhandling.exceptions.AppException;
import com.filip.examples.springbootspringdocopenapi3.repositories.UserRepository;
import com.filip.examples.springbootspringdocopenapi3.security.dtos.LoginRequest;
import com.filip.examples.springbootspringdocopenapi3.security.dtos.SignUpRequest;
import com.filip.examples.springbootspringdocopenapi3.security.jwt.ApiResponse;
import com.filip.examples.springbootspringdocopenapi3.security.jwt.JwtAuthenticationResponse;
import com.filip.examples.springbootspringdocopenapi3.security.jwt.JwtTokenProvider;
import com.filip.examples.springbootspringdocopenapi3.security.models.ApplicationUser;
import com.filip.examples.springbootspringdocopenapi3.security.models.Role;
import com.filip.examples.springbootspringdocopenapi3.security.repositories.ApplicationUserRepository;
import com.filip.examples.springbootspringdocopenapi3.security.repositories.RoleRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "Authentication requests api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping(value = "/signin", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(value = "/signup", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (applicationUserRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (applicationUserRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address is already taken!"), HttpStatus.BAD_REQUEST);
        }

        //Creating user's account
        ApplicationUser user = new ApplicationUser(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            throw new AppException("User Role not set.");
        }

        user.setRoles(Collections.singleton(userRole));

        user.setUserStatus(1);

        ApplicationUser result = applicationUserRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));

    }

}
