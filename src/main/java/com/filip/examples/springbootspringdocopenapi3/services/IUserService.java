package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    void createUser(User user);

    void createUsersWithArrayInput(List<User> users);

    void createUsersWithListInput(List<User> users);

    void deleteUser(String username);

    User getUserByName(String username);

    ResponseEntity<String> loginUser(String username, String password);

    void logoutUser();

    void updateUser(String username, User user);



}
