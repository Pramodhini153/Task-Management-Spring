package com.taskmang.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.taskmang.model.UserDto;

@FeignClient(name="USER-SERVICE",url="http://localhost:6001")
public interface UserService {

	@GetMapping("api/users/profile")
	public UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
