package com.taskmang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmang.model.User;
import com.taskmang.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt){
		
		User user=userservice.getUserProfile(jwt);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String jwt){
		
		List<User> users=userservice.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
}
