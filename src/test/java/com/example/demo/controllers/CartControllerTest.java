package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
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

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<ModifyCartRequest> json;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ItemRepository itemRepository;

    @Before
    public void setup() {
        given(userRepository.findByUsername(any())).willReturn(getUser());
        given(itemRepository.findById(any())).willReturn(Optional.of(getItem()));
    }

    @Test
    public void addTocart() throws Exception {
        mvc.perform(
                post(new URI("/api/cart/addToCart"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .content(json.write(getRequest()).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void addTocartItemNotFound() throws Exception {
        given(itemRepository.findById(any())).willReturn(Optional.empty());
        mvc.perform(
                post(new URI("/api/cart/addToCart"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .content(json.write(getRequest()).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void removeFromcart() throws Exception {
        mvc.perform(
                post(new URI("/api/cart/removeFromCart"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .content(json.write(getRequest()).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void removeFromcartItemNotFound() throws Exception {
        given(itemRepository.findById(any())).willReturn(Optional.empty());
        mvc.perform(
                post(new URI("/api/cart/removeFromCart"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .content(json.write(getRequest()).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    private Cart getCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(new ArrayList<>());
        cart.setTotal(BigDecimal.ZERO);
        return cart;
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setCart(getCart());
        return user;
    }

    private Item getItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("name");
        item.setDescription("description");
        item.setPrice(BigDecimal.ZERO);
        return item;
    }

    private ModifyCartRequest getRequest() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(1L);
        request.setQuantity(1);
        request.setUsername("username");
        return request;
    }
}