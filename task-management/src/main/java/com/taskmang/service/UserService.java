package com.taskmang.service;

import java.util.List;

import com.taskmang.model.User;

public interface UserService {

	public User getUserProfile(String jwt);
	
	public List<User> getAllUsers();
}
