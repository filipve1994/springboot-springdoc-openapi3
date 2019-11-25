package com.filip.examples.springbootspringdocopenapi3.services;

import com.filip.examples.springbootspringdocopenapi3.models.User;
import com.filip.examples.springbootspringdocopenapi3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
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
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.delete(user);

    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
        User us = getUserByName(user.getUsername());
        us.setUsername(username);
        userRepository.save(us);
    }

    @Override
    public List<User> getall() {
        return userRepository.findAll();
    }

}
