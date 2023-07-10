package com.revotech.thuctap.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revotech.thuctap.dto.CartDTO;
import com.revotech.thuctap.dto.UserDTO;
import com.revotech.thuctap.models.ResponseObject;
import com.revotech.thuctap.models.User;
import com.revotech.thuctap.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
    private UserRepository rep;
	
	public static UserDTO mapToDTO(User u) {
		ModelMapper mapper = new ModelMapper();
		UserDTO dto = new UserDTO();
    	dto = mapper.map(u, UserDTO.class);
    	List<CartDTO> cartdto = new ArrayList<>();
    	u.getCarts().forEach(c->{
    		cartdto.add(mapper.map(c, CartDTO.class));
    	});
    	dto.setCart(cartdto);
		return dto;
	}
	
	public UserDTO findByAccount(int acc) {
		return mapToDTO(rep.findByAccount_id(acc));
	}

	//get
    public ResponseEntity<ResponseObject> getAllUser() {
        List<User> foundUser = rep.findAll();
        List<UserDTO> result = new ArrayList<>();
        foundUser.forEach(u -> {
        	result.add(mapToDTO(u));
        });
        return !foundUser.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query User successfully", result))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Dont have any User!!!", ""));
    }

    //get
    public ResponseEntity<ResponseObject> getUserByID(Long id) {
        Optional<User> foundUser = rep.findById(id);
        return foundUser.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query User successfully", mapToDTO(foundUser.get())))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find User with id = " + id, ""));
    }

    //insert
    public ResponseEntity<ResponseObject> insertUser(User newUser) {
    	System.out.println(newUser);
        Optional<User> foundE = rep.findByCode(newUser.getCode());
        if (foundE.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "User already taken", ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert User successfully", rep.save(newUser)));
    }

    //update, insert = update if found, otherwise insert
    public ResponseEntity<ResponseObject> updateUser(User newUser, String code) {
        Optional<User> updatedUser = rep.findByCode(code);
        if (updatedUser.isPresent()) {
            newUser.setId(updatedUser.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update Product successfully", rep.save(newUser)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", "Cant find User by code=" + code, ""));
    }

    //delete
    public ResponseEntity<ResponseObject> deleteUser(String code) {
        Optional<User> exists = rep.findByCode(code);
        if (exists.isPresent()) {
            rep.deleteById(exists.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete User successfully", ""));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find User to delete", ""));
    }
}
