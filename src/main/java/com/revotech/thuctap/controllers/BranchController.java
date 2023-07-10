package com.revotech.thuctap.controllers;

import java.util.List;
import java.util.Optional;

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

import com.revotech.thuctap.models.Branch;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.repositories.BranchRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/branch")
public class BranchController {
    @Autowired
    private BranchRepository rep;

    // this request is: http://localhost:8080/api/v1/branch
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllBranch() {
        List<Branch> foundBranch = rep.findAll();
        return !foundBranch.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Branch successfully", foundBranch))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Dont have any Branch!!!", ""));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseObject> getEmployeeByID(@PathVariable String code) {
        Optional<Branch> foundBranch = rep.findByCode(code);
        return foundBranch.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Branch successfully", foundBranch))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find Branch with code = " + code, ""));
    }

    // insert
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Branch newBranch) {
        Optional<Branch> foundBranch = rep.findByCode(newBranch.getCode());
        if (foundBranch.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Branch already taken", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Branch successfully", rep.save(newBranch)));
    }

    // update, insert = update if found, otherwise insert
    @PutMapping("/{code}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Branch newBranch, @PathVariable String code) {
        Optional<Branch> foundBranch = rep.findByCode(code);
        if (foundBranch.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update Product successfully", rep.save(newBranch)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "Cant find Branch by code=" + code, ""));
    }

    @DeleteMapping("/{code}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String code) {
        Optional<Branch> exists = rep.findByCode(code);
        if (exists.isPresent()) {
            rep.deleteById(exists.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Branch successfully", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find Branch to delete", ""));
    }
}
