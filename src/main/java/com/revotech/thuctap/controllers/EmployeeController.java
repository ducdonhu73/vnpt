package com.revotech.thuctap.controllers;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.revotech.thuctap.models.Employee;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.repositories.EmployeeRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/employee")
public class EmployeeController {
	@Autowired
	private EmployeeRepository rep;

	//this request is: http://localhost:8080/api/v1/employee
	@GetMapping("")
	public ResponseEntity<ResponseObject> getAllEmployee() {
		Optional<List<Employee>> foundEmployee = Optional.ofNullable(rep.findAll());
        return foundEmployee.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query employee successfully", foundEmployee)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find employee", "")
                );
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> getEmployeeByID(@PathVariable Long id) {
		Optional<Employee> foundEmployee = rep.findById(id);
        return foundEmployee.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query employee successfully", foundEmployee)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find employee with id = "+id, "")
                );
	}

	//insert
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Employee newE) {
        //2 employee must not have the same!
        Optional<Employee> foundE = Optional.ofNullable(rep.findByCode(newE.getCode()));
        if(foundE.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed", "Employee already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
           new ResponseObject("ok", "Insert Employee successfully", rep.save(newE))
        );
    }

    //update, insert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Employee newE, @PathVariable Long id) {
    	ModelMapper mapper = new ModelMapper();
        Employee updatedE = rep.findById(id).map(e ->{
        	newE.setId(id);
        	mapper.map(newE, e);
        	return rep.save(e);
        }).orElseGet(() -> {
                    newE.setId(id);
                    return rep.save(newE);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successfully", updatedE)
        );
    }

    //Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = rep.existsById(id);
        if(exists) {
            rep.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }
}
