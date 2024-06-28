package com.taskmang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmang.config.JwtProvider;
import com.taskmang.model.User;
import com.taskmang.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public User getUserProfile(String jwt) {
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		return repo.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers(){
		return repo.findAll();
	}
}
