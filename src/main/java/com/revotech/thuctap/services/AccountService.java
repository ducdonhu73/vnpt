package com.revotech.thuctap.services;

import java.util.List;
import java.util.Optional;

import com.revotech.thuctap.services.interFace.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revotech.thuctap.models.Account;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.repositories.AccountRepository;

@Service
public class AccountService implements IAccountService {
	@Autowired
    private AccountRepository rep;

	//get
    @Override
    public List<Account> getAllAccount() {
        return rep.findAll();
    }

    //get
    @Override
    public ResponseEntity<ResponseObject> getAccountByID(String code) {
        Optional<Account> foundAccount = rep.findByCode(code);
        return foundAccount.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Account successfully", foundAccount))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find Account with code = " + code, ""));
    }

    @Override
    public Account getAcc(String code) {
    	return rep.findByCode(code).get();
    }

    //insert
    @Override
    public ResponseObject insertAcount(Account newAccount) {
        Optional<Account> foundAccount = rep.findByCode(newAccount.getCode());
        if (foundAccount.isPresent()) {
            return new ResponseObject("failed", "Account already taken", "");
        }
        return new ResponseObject("ok", "Insert Account successfully", rep.save(newAccount));
    }

    //update
    @Override
    public ResponseEntity<ResponseObject> updateAccount(Account newAccount, String code) {
        Optional<Account> foundAccount = rep.findByCode(code);
        if (foundAccount.isPresent()) {
        	Account updateAccount = foundAccount.get();
        	updateAccount.update(newAccount);
        	return ResponseEntity.status(HttpStatus.OK).body(
        			new ResponseObject("ok", "Update Product successfully", rep.save(updateAccount)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
    			new ResponseObject("failed", "Cant find Account by code="+code, ""));
    }

    //delete
    @Override
    public ResponseEntity<ResponseObject> deleteAccount(String code) {
    	Optional<Account> exists = rep.findByCode(code);
        if (exists.isPresent()) {
            rep.deleteById(exists.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Account successfully", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find Account to delete", ""));
    }
}
