package com.revotech.thuctap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.revotech.thuctap.models.Account;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.services.AccountService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
    // this request is: http://localhost:8080/api/v1/account
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllAccount() {
        List<Account> foundAccount = accountService.getAllAccount();
        return !foundAccount.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Account successfully", foundAccount))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Dont have any Account!!!", ""));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseObject> getAccountByID(@PathVariable String code) {
        return accountService.getAccountByID(code);
    }

    // insert
    @PostMapping("/insert")
    ResponseObject insertAcount(@RequestBody Account newAccount) {
        return accountService.insertAcount(newAccount);
    }

    // update, insert = update if found, otherwise insert
    @PutMapping("/{code}")
    ResponseEntity<ResponseObject> updateAccount(@RequestBody Account newAccount, @PathVariable String code) {
        return accountService.updateAccount(newAccount, code);
    }

    @DeleteMapping("/{code}")
    ResponseEntity<ResponseObject> deleteAccount(@PathVariable String code) {
    	return accountService.deleteAccount(code);
    }
}
