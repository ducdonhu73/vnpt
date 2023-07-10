package com.revotech.thuctap.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revotech.thuctap.dto.CartDTO;
import com.revotech.thuctap.models.Cart;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.repositories.CartRepository;

@Service
public class CartService {
	@Autowired
    private CartRepository rep;
	private ModelMapper mapper = new ModelMapper();

	//get
    public ResponseEntity<ResponseObject> getAllCart() {
        List<CartDTO> foundCart = new ArrayList<>();
        rep.findAll().forEach(c -> {
        	foundCart.add(mapper.map(c, CartDTO.class));
        });;
        return !foundCart.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Cart successfully", foundCart))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Dont have any Cart!!!", ""));
    }

    //get
    public ResponseEntity<ResponseObject> getCartByID(Integer id) {
        Optional<Cart> foundCart = rep.findById(id);
        return foundCart.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Cart successfully", mapper.map(foundCart, CartDTO.class)))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find Cart with id = " + id, ""));
    }

    //insert
    public ResponseObject insertAcount(Cart newCart) {
    	long millis=System.currentTimeMillis();   
    	java.util.Date now = new java.util.Date(millis); 
    	newCart.setTime(now);
    	System.out.println(newCart);
        return new ResponseObject("ok", "Insert Cart successfully", rep.save(newCart));
    }

    //update, insert = update if found, otherwise insert
    public ResponseEntity<ResponseObject> updateCart(Cart newCart, Integer id) {
    	System.out.println(newCart);
        Optional<Cart> foundCart = rep.findById(id);
        if (foundCart.isPresent()) {
            Cart updateCart = foundCart.get();
            updateCart.setUser(newCart.getUser());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update Product successfully", rep.save(updateCart)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "Cant find Cart by id=" + id, ""));
    }

    //delete
    public ResponseEntity<ResponseObject> deleteCart(Integer id) {
        Optional<Cart> exists = rep.findById(id);
        if (exists.isPresent()) {
            rep.deleteById(exists.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Cart successfully", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        		new ResponseObject("failed", "Cannot find Cart to delete", ""));
    }
}
