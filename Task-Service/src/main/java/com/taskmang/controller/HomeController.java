package com.taskmang.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmang.model.Task;
import com.taskmang.model.TaskStatus;
import com.taskmang.model.UserDto;

@RestController
public class HomeController {

	@GetMapping("/tasks")
	public ResponseEntity<String> getAssignedUsersTask(){
		
		
		return new ResponseEntity<>("Welcome to task Service",HttpStatus.OK);
	}
	
}
