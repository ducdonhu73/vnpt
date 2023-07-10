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

import com.revotech.thuctap.models.Supplier;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.repositories.SupplierRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/supplier")
public class SupplierController {
    @Autowired
    private SupplierRepository rep;

    // this request is: http://localhost:8080/api/v1/supplier
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllSupplier() {
        List<Supplier> foundSupplier = rep.findAll();
        return !foundSupplier.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Supplier successfully", foundSupplier))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Dont have any Supplier!!!", ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getEmployeeByID(@PathVariable Long id) {
        Optional<Supplier> foundSupplier = rep.findById(id);
        return foundSupplier.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query Supplier successfully", foundSupplier))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find Supplier with id = " + id, ""));
    }

    // insert
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Supplier newSupplier) {
        Optional<Supplier> found = rep.findByCode(newSupplier.getCode());
        if (found.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Supplier already taken", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Supplier successfully", rep.save(newSupplier)));
    }

    // update, insert = update if found, otherwise insert
    @PutMapping("/{code}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Supplier newSupplier, @PathVariable String code) {
        Optional<Supplier> updatedSupplier = rep.findByCode(code);
        if (updatedSupplier.isPresent()) {
            newSupplier.setId(updatedSupplier.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update Product successfully", rep.save(newSupplier)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "Cant find Supplier by code=" + code, ""));
    }

    @DeleteMapping("/{code}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String code) {
        Optional<Supplier> exists = rep.findByCode(code);
        if (exists.isPresent()) {
            rep.deleteById(exists.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Supplier successfully", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find Supplier to delete", ""));
    }
}
