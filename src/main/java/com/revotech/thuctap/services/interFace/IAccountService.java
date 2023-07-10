package com.revotech.thuctap.services.interFace;

import com.revotech.thuctap.models.Account;
import com.revotech.thuctap.models.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public List<Account> getAllAccount();

    //get
    public ResponseEntity<ResponseObject> getAccountByID(String code);

    public Account getAcc(String code);

    //insert
    public ResponseObject insertAcount(Account newAccount);

    //update
    public ResponseEntity<ResponseObject> updateAccount(Account newAccount, String code);

    //delete
    public ResponseEntity<ResponseObject> deleteAccount(String code);
}
