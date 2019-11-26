package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.dtos.NewUserDto;
import com.filip.examples.springbootspringdocopenapi3.dtos.UserProfileDto;
import com.filip.examples.springbootspringdocopenapi3.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    void createUser(NewUserDto newUserDto);

    void createUsersWithArrayInput(List<User> users);

    void createUsersWithListInput(List<User> users);

    void deleteUser(String username);

    void deleteUserById(Long id);

    User getUserByName(String username);

    User getUserById(Long id);

    ResponseEntity<String> loginUser(String username, String password);

    void logoutUser();

    void updateUser(String username, User user);


    void updateUserById(Long id, User user);

    void updateUsersPersonalInformation(String username, UserProfileDto userProfileDto);

    List<User> findAll();
}
