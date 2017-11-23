package com.stackroute.gitsearch.controllers;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.gitsearch.exceptions.DuplicateUserFoundException;
import com.stackroute.gitsearch.exceptions.UserNotFoundException;
import com.stackroute.gitsearch.model.User;
import com.stackroute.gitsearch.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userService")
public class UserController {

	HashMap<String, String> map = new HashMap<>();

	@Autowired
	private UserService userService;

	// <-- Save Methods -->
	@PostMapping(path = "/register")
	public @ResponseBody ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			userService.saveUser(user);

		} catch (DuplicateUserFoundException e) {

			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("success", "false");
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.CONFLICT);
		}
		map.clear();
		map.put("success", "true");
		map.put("message", "User registered successfully");

		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PostMapping(path = "/login")
	public @ResponseBody ResponseEntity<?> login(@RequestBody User login)
			throws ServletException, UserNotFoundException {

		String jwtToken = "";

		try {
			if (login.getUserId() == null || login.getPassword() == null) {
				throw new ServletException("Please fill in username and password");
			}

			String userId = login.getUserId();
			String password = login.getPassword();

			User user = userService.getUserById(userId);

			if (user == null) {
				throw new ServletException("User id not found.");
			}

			String pwd = user.getPassword();

			if (!password.equals(pwd)) {
				throw new ServletException("Invalid login. Please check your name and password.");
			}

			jwtToken = Jwts.builder().setSubject(userId).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

			map.clear();
			map.put("message", "User successfully logged in");
			map.put("token", jwtToken);

		} catch (Exception e) {

			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("token", null);
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
