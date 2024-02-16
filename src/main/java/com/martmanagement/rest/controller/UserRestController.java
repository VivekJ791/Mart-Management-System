package com.martmanagement.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martmanagement.request.UserDTO;
import com.martmanagement.service.UserService;

@RestController
@RequestMapping("/api/rest/users")
public class UserRestController {

	@Autowired UserService userService;	
	
	@PostMapping("/addOrUpdate")
    public ResponseEntity<Object> addOrUpdate(@RequestBody UserDTO userDTO) {
        try {
            UserDTO createdUser = userService.addOrUpdate(userDTO);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
