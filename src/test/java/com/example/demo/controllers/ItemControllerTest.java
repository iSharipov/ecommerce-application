package com.example.demo.controllers;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.ItemRepository;
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
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ItemRepository itemRepository;

    @Before
    public void setup() {
        given(userRepository.findByUsername(any())).willReturn(getUser());
        given(itemRepository.findById(any())).willReturn(Optional.of(getItem()));
        given(itemRepository.findByName(any())).willReturn(Collections.singletonList(getItem()));
    }

    @Test
    public void getItems() throws Exception {
        mvc.perform(
                get(new URI("/api/item"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void getItemById() throws Exception {
        mvc.perform(
                get(new URI("/api/item/1"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void getItemsByName() throws Exception {
        mvc.perform(
                get(new URI("/api/item/name/name"))
                        .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTk1NjQ2MDAxMzgsImlhdCI6MTU2NDYwMDEzOH0.NAmptX8nJSE3BT6a8wZoOOCMpFTpvumnQAdIZlWHNFeR8-zs1Xuw9iQEC6Voig_cU1DebfJIMHxIWXHZn8whCg")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    private Item getItem() {
        Item item = new Item();
        item.setId(1L);
        item.setName("name");
        item.setDescription("description");
        item.setPrice(BigDecimal.ZERO);
        return item;
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        return user;
    }
}