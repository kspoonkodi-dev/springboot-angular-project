package com.example.crud.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.dto.request.AuthRequest;
import com.example.crud.dto.response.AuthResponse;
import com.example.crud.service.jwt.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final JwtUtil jwtUtil;

	private final UserDetailsService userDetailsService;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
						authRequest.getPassword()));

		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

		String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority().replace("ROLE_", "");

		String token = jwtUtil.generateToken(userDetails);
		

		return ResponseEntity.ok(new AuthResponse(token, role));
	}
}
