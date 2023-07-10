package com.revotech.thuctap.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revotech.thuctap.models.Product;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
    private ProductRepository repository;

    //get
	public ResponseEntity<ResponseObject> getAllProducts() {
    	List<Product> listProduct = repository.findAll();
    	return listProduct.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", listProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Dont have any product", "")
                );
    }
    
//    Get detail a product
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
    
    //get
    public ResponseEntity<ResponseObject> findByCode(String code) {
        Optional<Product> foundProduct = Optional.ofNullable(repository.findByCode(code));
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find product with code = "+code, "")
                );
    }
    
    //insert
    public ResponseEntity<ResponseObject> insertProduct(Product newProduct) {
    	System.out.println(newProduct);
        if (repository.existsByCode(newProduct.getCode())) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
           new ResponseObject("ok", "Insert Product successfully", repository.save(newProduct))
        );
    }
    
    //insert many product
    public ResponseEntity<ResponseObject> insertProducts(List<Product> newProducts) {
    	List<String> err = new ArrayList<>();
    	for (Product product : newProducts) {			
    		boolean foundProducts = repository.existsByCode(product.getCode());
    		if(foundProducts) err.add(product.getCode());
    		else repository.save(product);
		}
    	if(err.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseObject("failed", "Product name already taken with code:", err)
					);
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "Insert Product successfully", newProducts)
				);
    }
    
    //update, insert = update if found, otherwise insert
    public ResponseEntity<ResponseObject> updateProduct(Product newProduct, Long id) {
    	System.out.println(newProduct);
        if (repository.existsById(id)) {
        	return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update Product successfully", repository.save(newProduct))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "Product is not exisit", "")
        );
    }
    
    //Delete
    public ResponseEntity<ResponseObject> deleteProduct(Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }
}
