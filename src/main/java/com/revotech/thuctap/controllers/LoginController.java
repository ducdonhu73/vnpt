package com.revotech.thuctap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revotech.thuctap.models.Account;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.repositories.AccountRepository;
import com.revotech.thuctap.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/login")
public class LoginController {
    @Autowired
    private AccountRepository accRep;
    @Autowired
    private UserService us;

    // this request is: http://localhost:8080/login
    @PostMapping("")
    ResponseEntity<ResponseObject> login(@RequestBody Account acc) {
        Account foundAccount = accRep.findByUsernameAndPassword(acc.getUsername(), acc.getPassword());
        if (foundAccount.getId() > 0) return ResponseEntity.status(HttpStatus.OK).body(new  ResponseObject("ok","success", us.findByAccount(foundAccount.getId())));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("falied", "something were wrong", false));
    }
}
