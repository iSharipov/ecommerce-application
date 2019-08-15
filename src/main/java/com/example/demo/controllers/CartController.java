package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final Logger logger = Logger.getLogger(CartController.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/addToCart")
    public ResponseEntity<Cart> addTocart(@RequestBody ModifyCartRequest request) {
        long start = System.currentTimeMillis();
        logger.info("[Request][addTocart] Request: " + request.toString());
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            logger.severe("[NOT_FOUND] user not found. HttpStatus: 404 Response time ms: " + (System.currentTimeMillis() - start));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<Item> item = itemRepository.findById(request.getItemId());
        if (!item.isPresent()) {
            logger.severe("[NOT_FOUND] item not found. HttpStatus: 404 Response time ms: " + (System.currentTimeMillis() - start));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Cart cart = user.getCart();
        IntStream.range(0, request.getQuantity())
                .forEach(i -> cart.addItem(item.get()));
        cartRepository.save(cart);
        logger.info("[Response][addTocart] Httpstatus 200. Response time ms: " + (System.currentTimeMillis()-start));
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<Cart> removeFromcart(@RequestBody ModifyCartRequest request) {
        long start = System.currentTimeMillis();
        logger.info("[Request][removeFromcart] Request: " + request.toString());
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            logger.severe("[NOT_FOUND] user not found. HttpStatus: 404  Response time ms: " + (System.currentTimeMillis() - start));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<Item> item = itemRepository.findById(request.getItemId());
        if (!item.isPresent()) {
            logger.severe("[NOT_FOUND] item not found. HttpStatus: 404 Response time ms: " + (System.currentTimeMillis() - start));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Cart cart = user.getCart();
        IntStream.range(0, request.getQuantity())
                .forEach(i -> cart.removeItem(item.get()));
        cartRepository.save(cart);
        logger.info("[Response][removeFromCart] Httpstatus 200. Response time ms: " + (System.currentTimeMillis()-start));
        return ResponseEntity.ok(cart);
    }

}
