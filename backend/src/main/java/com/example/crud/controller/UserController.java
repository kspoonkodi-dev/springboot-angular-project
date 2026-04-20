package com.example.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.entity.User;
import com.example.crud.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/*
	 * User Registration *
	 */
	@PostMapping("instructors/register")
	public ResponseEntity<User> createInstructor(@ModelAttribute User user) throws Exception {

		User newUser = userService.registerUser(user);
		return ResponseEntity.ok(newUser);

	}


}
