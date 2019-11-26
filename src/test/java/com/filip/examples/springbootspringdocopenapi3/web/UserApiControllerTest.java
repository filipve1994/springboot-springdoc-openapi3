package com.filip.examples.springbootspringdocopenapi3.web;

import com.filip.examples.springbootspringdocopenapi3.services.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private IUserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/all"))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(content().json("{\"id\":1,\"title\":\"delectus aut autem\",\"userId\":1,\"completed\":false}"))
        ;
    }

    @Test
    void createUser() {
    }

    @Test
    void createUsersWithArrayInput() {
    }

    @Test
    void createUsersWithListInput() {
    }

    @Test
    void createUsersWithListInput2() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserByName() {
    }

    @Test
    void loginUser() {
    }

    @Test
    void logoutUser() {
    }

    @Test
    void updateUser() {
    }
}