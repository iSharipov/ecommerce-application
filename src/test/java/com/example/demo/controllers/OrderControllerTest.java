package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private OrderRepository orderRepository;

    @Before
    public void setup() {
        given(userRepository.findByUsername(any())).willReturn(getUser());
    }

    @Test
    public void submit() throws Exception {
        mvc.perform(
                post(new URI("/api/order/submit/username"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void submitWhenUserNotFound() throws Exception {
        given(userRepository.findByUsername(any())).willReturn(getUser()).willReturn(null);
        mvc.perform(
                post(new URI("/api/order/submit/username"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getOrdersForUser() throws Exception {
        mvc.perform(
                get(new URI("/api/order/history/username"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void getOrdersForUserWhenUserNotFound() throws Exception {
        given(userRepository.findByUsername(any())).willReturn(getUser()).willReturn(null);
        mvc.perform(
                get(new URI("/api/order/history/username"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setCart(getCart());
        return user;
    }

    private Cart getCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(new ArrayList<>());
        cart.setTotal(BigDecimal.ZERO);
        return cart;
    }
}