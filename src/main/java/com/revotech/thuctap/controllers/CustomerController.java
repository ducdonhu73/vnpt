//package com.revotech.thuctap.controllers;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.revotech.thuctap.models.Customer;
//import com.revotech.thuctap.models.ResponseObject;
//import com.revotech.thuctap.repositories.CustomerRepository;
//
//@CrossOrigin
//@RestController
//@RequestMapping(path = "/api/v1/customer")
//public class CustomerController {
//    @Autowired
//    private CustomerRepository rep;
//
//    // this request is: http://localhost:8080/api/v1/customer
//    @GetMapping("")
//    public ResponseEntity<ResponseObject> getAllCustomer() {
//        List<Customer> foundCustomer = rep.findAll();
//        return !foundCustomer.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Query Customer successfully", foundCustomer))
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("failed", "Dont have any customer!!!", ""));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseObject> getEmployeeByID(@PathVariable Long id) {
//        Optional<Customer> foundCustomer = rep.findById(id);
//        return foundCustomer.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Query Customer successfully", foundCustomer))
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("failed", "Cannot find Customer with id = " + id, ""));
//    }
//
//    // insert
//    @PostMapping("/insert")
//    ResponseEntity<ResponseObject> insertProduct(@RequestBody Customer newCustomer) {
//        // 2 employee must not have the same!
//        Optional<Customer> foundE = rep.findByCode(newCustomer.getCode());
//        if (foundE.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new ResponseObject("failed", "Customer already taken", ""));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok", "Insert Customer successfully", rep.save(newCustomer)));
//    }
//
//    // update, insert = update if found, otherwise insert
//    @PutMapping("/{code}")
//    ResponseEntity<ResponseObject> updateProduct(@RequestBody Customer newCustomer, @PathVariable String code) {
//        Optional<Customer> updatedCustomer = rep.findByCode(code);
//        if (updatedCustomer.isPresent()) {
//        	newCustomer.setId(updatedCustomer.get().getId());
//        	return ResponseEntity.status(HttpStatus.OK).body(
//        			new ResponseObject("ok", "Update Product successfully", rep.save(newCustomer)));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//    			new ResponseObject("failed", "Cant find customer by code="+code, ""));
//    }
//
//    @DeleteMapping("/{code}")
//    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String code) {
//    	Optional<Customer> exists = rep.findByCode(code);
//        if (exists.isPresent()) {
//            rep.deleteById(exists.get().getId());
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Delete customer successfully", ""));
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                new ResponseObject("failed", "Cannot find customer to delete", ""));
//    }
//}
