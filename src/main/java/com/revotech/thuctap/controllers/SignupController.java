package com.revotech.thuctap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revotech.thuctap.models.Account;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.models.User;
import com.revotech.thuctap.services.AccountService;
import com.revotech.thuctap.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/signup")
public class SignupController {
	@Autowired
    private AccountService as;
	@Autowired
    private UserService us;
	
    // this request is: http://localhost:8080/signup
	@PostMapping("")
    ResponseEntity<ResponseObject> signup(@RequestBody Account newAccount) {
		as.insertAcount(newAccount);
		newAccount = as.getAcc(newAccount.getCode());
		System.out.println(newAccount);
		User newUser = new User();
        String avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwbGozsS9QP10p16rZiCrQD0koXVkI4c7LwUHab9dkmFRcN0VqCkB37f2y0EnySItwykg&usqp=CAU";
        newUser.setAvatar(avatar);
        newUser.setAccount(newAccount);
        newUser.setCode("U" + String.format("%03d", newAccount.getId()));
        return us.insertUser(newUser);
    }
}
