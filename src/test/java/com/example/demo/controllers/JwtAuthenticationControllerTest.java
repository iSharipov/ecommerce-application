package com.example.demo.controllers;

import com.example.demo.config.security.JwtRequest;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class JwtAuthenticationControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<JwtRequest> json;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() {
        given(userRepository.findByUsername(any())).willReturn(getUser());
    }

    @Test
    public void createAuthenticationToken() throws Exception {
        mvc.perform(
                post(new URI("/login"))
                        .content(json.write(getRequest()).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    private JwtRequest getRequest() {
        JwtRequest request = new JwtRequest();
        request.setUsername("username");
        request.setPassword("password");
        return request;
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("$2a$10$zJc2V6uJxYDKVbWW/9JIG.NWqyEe3EMicGtMEiGHYq3VKdCHTlNXW");
        return user;
    }
}