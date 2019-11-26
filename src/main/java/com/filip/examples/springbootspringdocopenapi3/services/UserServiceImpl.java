package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.dtos.NewUserDto;
import com.filip.examples.springbootspringdocopenapi3.dtos.UserProfileDto;
import com.filip.examples.springbootspringdocopenapi3.errorhandling.exceptions.UserNotFoundException;
import com.filip.examples.springbootspringdocopenapi3.errorhandling.exceptions.UsernameAlreadyExistsException;
import com.filip.examples.springbootspringdocopenapi3.models.User;
import com.filip.examples.springbootspringdocopenapi3.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(NewUserDto newUserDto) {

        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setFirstName(newUserDto.getFirstName());
        user.setLastName(newUserDto.getLastName());
        user.setEmail(newUserDto.getEmail());
        user.setPassword(newUserDto.getPassword());
        user.setPhone(newUserDto.getPhone());
        user.setUserStatus(1);

        userRepository.save(user);
    }

    @Override
    public void createUsersWithArrayInput(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public void createUsersWithListInput(List<User> users) {
        createUsersWithArrayInput(users);
    }

    @Override
    public void deleteUser(String username) {
        User user = getUserByName(username);
        userRepository.delete(user);

    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id : " + id + " doesn't exist"));
        userRepository.delete(user);
    }

    @Override
    public User getUserByName(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User doesn't exist with username: " + username);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id-" + id));
    }

    @Override
    public ResponseEntity<String> loginUser(String username, String password) {
        Instant now = Instant.now().plus(1, ChronoUnit.HOURS);
        return ResponseEntity.ok()
                .header("X-Expires-After", new Date(now.toEpochMilli()).toString())
                .header("X-Rate-Limit", "5000")
                .body("logged in user session:" + now.toEpochMilli());
    }

    @Override
    public void logoutUser() {

    }

    @Override
    public void updateUser(String username, User user) {
        User us = getUserByName(username);
        us.setUsername(user.getUsername());
        userRepository.save(us);
    }

    @Override
    public void updateUserById(Long id, User user) {
        User us = getUserById(id);
        us.setUsername(user.getUsername());
        userRepository.save(us);
    }

    @Override
    public void updateUsersPersonalInformation(String username, UserProfileDto userProfileDto) {
        User userFromDB = getUserByName(username);
        userFromDB.setFirstName(userProfileDto.getFirstName());
        userFromDB.setLastName(userProfileDto.getLastName());
        userFromDB.setPhone(userProfileDto.getPhone());
        userRepository.save(userFromDB);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
