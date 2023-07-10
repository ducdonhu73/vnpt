package com.revotech.thuctap.controllers;

import com.revotech.thuctap.models.Product;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    //DI = Dependency Injection
    @Autowired
    private ProductService service;

    @GetMapping("")
    //this request is: http://localhost:8080/api/v1/products
    ResponseEntity<ResponseObject> getAllProducts() {
    	System.out.println("getAllProducts");
    	return service.getAllProducts();
    }
    
//    Get detail a product
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
//        Optional<Product> foundProduct = repository.findById(id);
//        return foundProduct.isPresent() ?
//                ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("ok", "Query product successfully", foundProduct)
//                ):
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("failed", "Cannot find product with id = "+id, "")
//                );
//    }
    
    @GetMapping("/{code}")
    ResponseEntity<ResponseObject> findByCode(@PathVariable String code) {
        return service.findByCode(code);
    }
    
    //insert
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
    	return service.insertProduct(newProduct);
    }
    
    //insert many product
    @PostMapping("/insertProducts")
    ResponseEntity<ResponseObject> insertProducts(@RequestBody List<Product> newProducts) {
    	return service.insertProducts(newProducts);
    }
    
    //update, insert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
    	return service.updateProduct(newProduct, id);
    }
    
    //Delete a Product by id
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
    	return service.deleteProduct(id);
    }
}
