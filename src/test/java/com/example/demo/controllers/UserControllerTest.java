package com.example.demo.controllers;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<CreateUserRequest> json;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() {
        given(userRepository.findByUsername(any())).willReturn(getUser());
        given(userRepository.findById(any())).willReturn(Optional.of(getUser()));
    }

    @Test
    public void findById() throws Exception {
        mvc.perform(
                get(new URI("/api/user/id/1"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void findByUserName() throws Exception {
        mvc.perform(
                get(new URI("/api/user/username"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void createUser() throws Exception {
        mvc.perform(
                post(new URI("/api/user/create"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .content(json.write(getRequest()).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        return user;
    }

    private CreateUserRequest getRequest() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("username");
        request.setPassword("password");
        return request;
    }
}