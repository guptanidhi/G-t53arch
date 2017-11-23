package com.stackroute.gitsearch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.gitsearch.exceptions.UserNotFoundException;
import com.stackroute.gitsearch.model.User;
import com.stackroute.gitsearch.services.UserService;

@RestController
@RequestMapping("/secureService")
public class SecureController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/loginSuccess")
	public String loginSuccess() {
		return "Login Successful!";
	}

	@GetMapping(path = "/user/{userId}")
	public User findById(@PathVariable String userId) throws UserNotFoundException {
		return userService.getUserById(userId);

	}
}
