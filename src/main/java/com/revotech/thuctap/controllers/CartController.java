package com.revotech.thuctap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revotech.thuctap.models.Cart;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.services.CartService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/cart")
public class CartController {
    @Autowired
    private CartService service;

    // this request is: http://localhost:8080/api/v1/cart
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllCart() {
        return service.getAllCart();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getCartByID(@PathVariable Integer id) {
        return service.getCartByID(id);
    }

    // insert
    @PostMapping("/insert")
    ResponseObject insertAcount(@RequestBody Cart newCart) {
    	return service.insertAcount(newCart);
    }

    // update, insert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateCart(@RequestBody Cart newCart, @PathVariable Integer id) {
    	return service.updateCart(newCart, id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteCart(@PathVariable Integer id) {
        return service.deleteCart(id);
    }
}
