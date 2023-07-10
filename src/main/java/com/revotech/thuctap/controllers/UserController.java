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

import com.revotech.thuctap.models.User;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService service;

    // this request is: http://localhost:8080/api/v1/user
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUser() {
        return service.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserByID(@PathVariable Long id) {
        return service.getUserByID(id);
    }

    // insert
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {
    	return service.insertUser(newUser);
    }

    // update, insert = update if found, otherwise insert
    @PutMapping("/{code}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable String code) {
        return service.updateUser(newUser, code);
    }

    @DeleteMapping("/{code}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable String code) {
        return service.deleteUser(code);
    }
}
