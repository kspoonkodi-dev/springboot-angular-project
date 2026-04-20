package com.example.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.crud.entity.User;
import com.example.crud.entity.User.Role;
import com.example.crud.exception.UserAlreadyExistsException;
import com.example.crud.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	public User registerUser(User user) {

		if (userRepository.existsByUsername(user.getUsername())) {

			throw new UserAlreadyExistsException("Username already registered");
		}

		if (userRepository.existsByEmail(user.getEmail())) {

			throw new UserAlreadyExistsException("Email already registered");
		}

		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(Role.INSTRUCTOR);
		System.out.println(user.getRole().name());
		System.out.println(user);
		return userRepository.save(user);
	}

}
